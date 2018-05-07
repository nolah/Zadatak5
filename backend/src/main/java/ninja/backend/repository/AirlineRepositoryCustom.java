package ninja.backend.repository;

import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface AirlineRepositoryCustom {

    List<Airline> findByName(String name);

    List<Airline> findByDescription(String description);

    List<Airline> findByLuggageDetails(Optional<String> luggageDetails);

    List<Airline> findByLuggageDetailsMandatory(String luggageDetails);

    List<Airline> airlines();

    Optional<Airline> readAirline(Long id);

}
