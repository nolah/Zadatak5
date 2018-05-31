package ninja.backend.repository;

import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface FlightReservationRepositoryCustom {

    List<FlightReservation> findByDepartingFlight(Optional<Long> departingFlightId);

    List<FlightReservation> findByDepartingFlightMandatory(Long departingFlightId);

    List<FlightReservation> findByReturningFlight(Optional<Long> returningFlightId);

    List<FlightReservation> findByReturningFlightMandatory(Long returningFlightId);

    List<FlightReservation> findByUser(Long userId);

    List<FlightReservation> findByPaymentMethod(PaymentMethod paymentMethod);

}
