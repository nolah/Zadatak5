package ninja.backend.repository;

import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;


public interface ImageRepositoryCustom {

    List<Image> findByFile(String filePath);

    List<Image> findByAircraft(Long aircraftId);

}
