package ninja.backend.api;

import javax.inject.Inject;

import org.slf4j.*;

import ninja.backend.model.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;

import java.util.*;
import java.util.stream.*;
import ninja.backend.model.enumeration.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FileApi {

    private final Logger log = LoggerFactory.getLogger(FileApi.class);

    public void findFile(FindFileRequest dto) {
        log.debug("findFile {}", dto);

        throw new UnsupportedOperationException();
    }

}
