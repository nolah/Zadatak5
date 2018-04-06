
package ninja.backend.service;

import ninja.backend.api.dto.FileUploadDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;

import ninja.backend.config.CustomProperties;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Base64Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;


@Service
public class FileStorageService {

    private final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Inject
    private CustomProperties customProperties;

    public void store(Optional<FileUploadDTO> fileDto, String relativeFilePath) throws IOException {
        if (fileDto.isPresent()) {
            store(fileDto.get(), relativeFilePath);
        }
    }

    public void store(FileUploadDTO fileDto, String relativeFilePath) throws IOException {

        log.debug(".store(fileName: {}, relativeFilePath: {}", fileDto.getFileName(), relativeFilePath);

        final byte[] content = Base64Utils.decodeFromString(fileDto.getBase64());
        final String absoluteFilePath = customProperties.getStorageFolder() + "/" + relativeFilePath;
        final File file = new File(absoluteFilePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Files.write(Paths.get(file.getPath()), content);
    }

    public void update(Optional<String> oldFilePath, Optional<FileUploadDTO> newFileDto, String relativeFilePath) throws IOException {
        if (oldFilePath.isPresent() && newFileDto.map(FileUploadDTO::getBase64).isPresent()) {
            update(oldFilePath.get(), newFileDto.get());
        } else if (oldFilePath.isPresent() && !newFileDto.isPresent()) {
            delete(oldFilePath.get());
        } else if (!oldFilePath.isPresent() && newFileDto.isPresent()) {
            store(newFileDto.get(), relativeFilePath);
        }
    }

    public void update(String oldFilePath, FileUploadDTO newFileDto) throws IOException {

        log.debug(".update(oldFilePath: {}, newFileName: {}", oldFilePath, newFileDto.getFileName());

        final byte[] content = Base64Utils.decodeFromString(newFileDto.getBase64());
        final String[] splitOldFilePath = oldFilePath.split("/");
        final String key = splitOldFilePath[splitOldFilePath.length - 2];
        final String oldFileName = splitOldFilePath[splitOldFilePath.length - 1];

        final String filePath = customProperties.getStorageFolder() + "/" + key;

        final File oldFile = new File(filePath, oldFileName);
        final byte[] oldFileContent = IOUtils.toByteArray(new FileInputStream(oldFile));
        final File newFile = new File(filePath, newFileDto.getFileName());

        if (Arrays.equals(oldFileContent, content) && oldFileName.equals(newFileDto.getFileName())) {
            return;
        }
        if (oldFile.exists()) {
            oldFile.delete();
        }
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        Files.write(Paths.get(newFile.getPath()), content);
    }

    public Optional<BufferedInputStream> retrieve(String key, String fileName) throws IOException {

        log.debug(".retrieve(key: {}, fileName: {}", key, fileName);

        final String filePath = customProperties.getStorageFolder() + "/" + key + "/" + fileName;
        final File file = new File(filePath);

        return Optional.of(new BufferedInputStream(new FileInputStream(file)));
    }

    public boolean delete(Optional<ninja.backend.model.File> fileModel) {
        return fileModel.isPresent() && delete(fileModel.get().getPath());
    }

    public boolean delete(String filePath) {

        log.debug(".delete(filePath: {}", filePath);

        final String path = customProperties.getStorageFolder() + "/" + filePath;
        final File file = new File(path);

        if (!file.exists()) {
            return false;
        }
        final File parent = file.getParentFile();

        return file.delete() && parent.delete();
    }

    public String generateFilePath(Optional<FileUploadDTO> newFileDto) {
        return newFileDto.isPresent() ? UUID.randomUUID().toString() + "/" + newFileDto.get().getFileName() : null;
    }

    public String updateFilePath(String oldFilePath, FileUploadDTO newFileDto) {
        if (oldFilePath.endsWith(newFileDto.getFileName()) && newFileDto.getBase64() == null) {
            return oldFilePath;
        }
        return updatePath(oldFilePath, newFileDto.getFileName());
    }

    public String updateFilePath(Optional<String> oldFilePath, Optional<FileUploadDTO> newFileDto) {
        if (!oldFilePath.isPresent() && newFileDto.isPresent()) {
            return generateFilePath(newFileDto);
        } else if (newFileDto.map(FileUploadDTO::getBase64).isPresent()) {
            return updatePath(oldFilePath.get(), newFileDto.get().getFileName());
        } else if (newFileDto.isPresent() && oldFilePath.isPresent()) {
            return oldFilePath.get();
        }
        return null;
    }

    private String updatePath(String oldFilePath, String fileName) {
        return oldFilePath.split("/")[0] + "/" + fileName;
    }
}