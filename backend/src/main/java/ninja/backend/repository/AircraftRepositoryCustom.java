package ninja.backend.repository;

import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;


public interface AircraftRepositoryCustom {

    List<Aircraft> findByMaker(String maker);

    List<Aircraft> findByType(String type);

    List<Aircraft> aircrafts();

    Optional<Aircraft> readAircraft(Long id);

}
