package ninja.backend.repository;

import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface AircraftRepositoryCustom {

    List<Aircraft> findByMaker(String maker);

    List<Aircraft> findByType(String type);

    List<Aircraft> findByAirline(Long airlineId);

    List<AircraftAircraftsTuple> aircrafts();

    Optional<Aircraft> readAircraft(Long id);

}
