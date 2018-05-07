package ninja.backend.repository;

import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface ImageRepositoryCustom {

    List<Image> findByFile(String filePath);

    List<Image> findByAircraft(Long aircraftId);

}
