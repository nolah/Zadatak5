package ninja.backend.repository.impl;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.FlightRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class FlightRepositoryImpl implements FlightRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(FlightRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Flight> findByAircraft(Long aircraftId) {
        log.trace(".findByAircraft(aircraftId: {})", aircraftId);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.aircraft.id.eq(aircraftId)).fetch();
    }

    @Override
    public List<Flight> findByTimestamp(ZonedDateTime timestamp) {
        log.trace(".findByTimestamp(timestamp: {})", timestamp);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.timestamp.eq(timestamp)).fetch();
    }

    @Override
    public List<Flight> findByNumberOfEconomySeats(Integer numberOfEconomySeats) {
        log.trace(".findByNumberOfEconomySeats(numberOfEconomySeats: {})", numberOfEconomySeats);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.numberOfEconomySeats.eq(numberOfEconomySeats)).fetch();
    }

    @Override
    public List<Flight> findByPriceOfEconomySeat(BigDecimal priceOfEconomySeat) {
        log.trace(".findByPriceOfEconomySeat(priceOfEconomySeat: {})", priceOfEconomySeat);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.priceOfEconomySeat.eq(priceOfEconomySeat)).fetch();
    }

    @Override
    public List<Flight> findByNumberOfBusinessSeats(Integer numberOfBusinessSeats) {
        log.trace(".findByNumberOfBusinessSeats(numberOfBusinessSeats: {})", numberOfBusinessSeats);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.numberOfBusinessSeats.eq(numberOfBusinessSeats)).fetch();
    }

    @Override
    public List<Flight> findByPriceOfBusinessSeats(BigDecimal priceOfBusinessSeats) {
        log.trace(".findByPriceOfBusinessSeats(priceOfBusinessSeats: {})", priceOfBusinessSeats);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.priceOfBusinessSeats.eq(priceOfBusinessSeats)).fetch();
    }

    @Override
    public List<Flight> findByFromAirport(String fromAirport) {
        log.trace(".findByFromAirport(fromAirport: {})", fromAirport);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.fromAirport.eq(fromAirport)).fetch();
    }

    @Override
    public List<Flight> findByToAirport(String toAirport) {
        log.trace(".findByToAirport(toAirport: {})", toAirport);
        final QFlight flight = QFlight.flight;
        return factory.select(flight).from(flight).where(flight.toAirport.eq(toAirport)).fetch();
    }

    @Override
    public List<FlightFlightsTuple> flights() {
        log.trace(".flights()");
        final QFlight flight = QFlight.flight;
        final QAircraft aircraft = QAircraft.aircraft;
        final QAirline airline = QAirline.airline;
        return factory.select(flight, aircraft, airline).from(flight).innerJoin(flight.aircraft, aircraft).innerJoin(aircraft.airline, airline).fetch().stream()
                .map(t -> new FlightFlightsTuple(t.get(flight), t.get(aircraft), t.get(airline))).collect(Collectors.toList());
    }

    @Override
    public Optional<Flight> readFlight(Long id) {
        log.trace(".readFlight(id: {})", id);
        final QFlight flight = QFlight.flight;
        return Optional.ofNullable(factory.select(flight).from(flight).where(flight.id.eq(id)).fetchOne());
    }

}
