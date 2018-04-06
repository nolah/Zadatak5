package ninja.backend.web.rest;

import java.util.Optional;

import javax.inject.Inject;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import ninja.backend.api.*;
import ninja.backend.model.*;
import ninja.backend.api.dto.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.service.FileStorageService;
import java.io.*;
import java.net.*;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/")
public class FileApiResource {

    private final Logger log = LoggerFactory.getLogger(FileApiResource.class);

    @Inject
    private FileStorageService fileStorageService;

    @Inject
    private FileApi fileApi;

    @RequestMapping(value = "/file/{key}/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> findFile(HttpServletResponse response, @PathVariable String key, @PathVariable String fileName) throws IOException {
        log.debug("GET /file/{}/{}", key, fileName);

        final Optional<BufferedInputStream> result = fileStorageService.retrieve(key, fileName);

        if (result.isPresent()) {
            final String mimeType = URLConnection.guessContentTypeFromStream(result.get());
            final HttpHeaders headers = new HttpHeaders();
            final int oneWeek = 7 * 24 * 60 * 60;
            response.setHeader("Cache-Control", "no-cache, no-store, max-age=" + oneWeek + ", must-revalidate");
            response.setContentType(mimeType);

            IOUtils.copy(result.get(), response.getOutputStream());
            return ResponseEntity.ok().headers(headers).build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
