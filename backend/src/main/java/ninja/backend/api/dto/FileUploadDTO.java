package ninja.backend.api.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import eu.execom.fabut.property.PropertyPath;


public class FileUploadDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<String> FILE_NAME = new PropertyPath<>("fileName");
    public static final PropertyPath<String> BASE_64 = new PropertyPath<>("base64");

    @NotNull
    private String fileName;

    @NotNull
    private String base64;

    private FileUploadDTO() {
    }

    public FileUploadDTO(String fileName, String base64) {
        this.fileName = fileName;
        this.base64 = base64;
    }

    public String getFileName() {
        return fileName;
    }

    public String getBase64() {
        return base64;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FileUploadDTO other = (FileUploadDTO) obj;
        if (this.fileName != null && other.fileName != null && !this.fileName.equals(other.fileName))
            return false;
        if (this.base64 != null && other.base64 != null && !this.base64.equals(other.base64))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.fileName == null) ? 0 : this.fileName.hashCode());
        result = prime * result + ((this.base64 == null) ? 0 : this.base64.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FileUploadDTO[" + "this.fileName=" + this.fileName + "]";
    }

}
