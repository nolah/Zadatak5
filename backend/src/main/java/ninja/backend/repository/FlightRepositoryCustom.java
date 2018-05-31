package ninja.backend.repository;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface FlightRepositoryCustom {

    List<FlightSearchFlightsTuple> searchFlights(ZonedDateTime fromDate, ZonedDateTime toDate, String fromAirport, String toAirport);

    List<Flight> findByAircraft(Long aircraftId);

    List<Flight> findByTimestamp(ZonedDateTime timestamp);

    List<Flight> findByNumberOfEconomySeats(Integer numberOfEconomySeats);

    List<Flight> findByFreeEconomySeats(Integer freeEconomySeats);

    List<Flight> findByPriceOfEconomySeat(BigDecimal priceOfEconomySeat);

    List<Flight> findByNumberOfBusinessSeats(Integer numberOfBusinessSeats);

    List<Flight> findByFreeBusinessSeats(Integer freeBusinessSeats);

    List<Flight> findByPriceOfBusinessSeats(BigDecimal priceOfBusinessSeats);

    List<Flight> findByFromAirport(String fromAirport);

    List<Flight> findByToAirport(String toAirport);

    List<FlightFlightsTuple> flights();

    Optional<Flight> readFlight(Long id);

}
