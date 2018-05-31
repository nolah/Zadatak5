package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.FlightReservationRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class FlightReservationRepositoryImpl implements FlightReservationRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(FlightReservationRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<FlightReservation> findByDepartingFlight(Optional<Long> departingFlightId) {
        log.trace(".findByDepartingFlight(departingFlightId: {})", departingFlightId);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(departingFlightId.map(flightReservation.departingFlight.id::eq).orElse(null)).fetch();
    }

    @Override
    public List<FlightReservation> findByDepartingFlightMandatory(Long departingFlightId) {
        log.trace(".findByDepartingFlightMandatory(departingFlightId: {})", departingFlightId);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(flightReservation.departingFlight.id.eq(departingFlightId)).fetch();
    }

    @Override
    public List<FlightReservation> findByReturningFlight(Optional<Long> returningFlightId) {
        log.trace(".findByReturningFlight(returningFlightId: {})", returningFlightId);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(returningFlightId.map(flightReservation.returningFlight.id::eq).orElse(null)).fetch();
    }

    @Override
    public List<FlightReservation> findByReturningFlightMandatory(Long returningFlightId) {
        log.trace(".findByReturningFlightMandatory(returningFlightId: {})", returningFlightId);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(flightReservation.returningFlight.id.eq(returningFlightId)).fetch();
    }

    @Override
    public List<FlightReservation> findByUser(Long userId) {
        log.trace(".findByUser(userId: {})", userId);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(flightReservation.user.id.eq(userId)).fetch();
    }

    @Override
    public List<FlightReservation> findByPaymentMethod(PaymentMethod paymentMethod) {
        log.trace(".findByPaymentMethod(paymentMethod: {})", paymentMethod);
        final QFlightReservation flightReservation = QFlightReservation.flightReservation;
        return factory.select(flightReservation).from(flightReservation).where(flightReservation.paymentMethod.eq(paymentMethod)).fetch();
    }

}
