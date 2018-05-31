package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.FlightPassengerRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class FlightPassengerRepositoryImpl implements FlightPassengerRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(FlightPassengerRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<FlightPassenger> findByFlightReservation(Long flightReservationId) {
        log.trace(".findByFlightReservation(flightReservationId: {})", flightReservationId);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.flightReservation.id.eq(flightReservationId)).fetch();
    }

    @Override
    public List<FlightPassenger> findByFirstName(String firstName) {
        log.trace(".findByFirstName(firstName: {})", firstName);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.firstName.eq(firstName)).fetch();
    }

    @Override
    public List<FlightPassenger> findByLastName(String lastName) {
        log.trace(".findByLastName(lastName: {})", lastName);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.lastName.eq(lastName)).fetch();
    }

    @Override
    public List<FlightPassenger> findByPassportNumber(String passportNumber) {
        log.trace(".findByPassportNumber(passportNumber: {})", passportNumber);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.passportNumber.eq(passportNumber)).fetch();
    }

    @Override
    public List<FlightPassenger> findBySmallCabinLuggage(Boolean smallCabinLuggage) {
        log.trace(".findBySmallCabinLuggage(smallCabinLuggage: {})", smallCabinLuggage);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.smallCabinLuggage.eq(smallCabinLuggage)).fetch();
    }

    @Override
    public List<FlightPassenger> findByLargeCabinLuggage(Boolean largeCabinLuggage) {
        log.trace(".findByLargeCabinLuggage(largeCabinLuggage: {})", largeCabinLuggage);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.largeCabinLuggage.eq(largeCabinLuggage)).fetch();
    }

    @Override
    public List<FlightPassenger> findByCheckedBag(Boolean checkedBag) {
        log.trace(".findByCheckedBag(checkedBag: {})", checkedBag);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.checkedBag.eq(checkedBag)).fetch();
    }

    @Override
    public List<FlightPassenger> findBySeatType(SeatType seatType) {
        log.trace(".findBySeatType(seatType: {})", seatType);
        final QFlightPassenger flightPassenger = QFlightPassenger.flightPassenger;
        return factory.select(flightPassenger).from(flightPassenger).where(flightPassenger.seatType.eq(seatType)).fetch();
    }

}
