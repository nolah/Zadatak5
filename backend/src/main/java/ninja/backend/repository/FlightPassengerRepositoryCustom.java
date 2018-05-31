package ninja.backend.repository;

import java.util.List;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface FlightPassengerRepositoryCustom {

    List<FlightPassenger> findByFlightReservation(Long flightReservationId);

    List<FlightPassenger> findByFirstName(String firstName);

    List<FlightPassenger> findByLastName(String lastName);

    List<FlightPassenger> findByPassportNumber(String passportNumber);

    List<FlightPassenger> findBySmallCabinLuggage(Boolean smallCabinLuggage);

    List<FlightPassenger> findByLargeCabinLuggage(Boolean largeCabinLuggage);

    List<FlightPassenger> findByCheckedBag(Boolean checkedBag);

    List<FlightPassenger> findBySeatType(SeatType seatType);

}
