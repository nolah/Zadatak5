package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.AirlineRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class AirlineRepositoryImpl implements AirlineRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(AirlineRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Airline> findByName(String name) {
        log.trace(".findByName(name: {})", name);
        final QAirline airline = QAirline.airline;
        return factory.select(airline).from(airline).where(airline.name.eq(name)).fetch();
    }

    @Override
    public List<Airline> findByDescription(String description) {
        log.trace(".findByDescription(description: {})", description);
        final QAirline airline = QAirline.airline;
        return factory.select(airline).from(airline).where(airline.description.eq(description)).fetch();
    }

    @Override
    public List<Airline> findByLuggageDetails(Optional<String> luggageDetails) {
        log.trace(".findByLuggageDetails(luggageDetails: {})", luggageDetails);
        final QAirline airline = QAirline.airline;
        return factory.select(airline).from(airline).where(luggageDetails.map(airline.luggageDetails::eq).orElse(null)).fetch();
    }

    @Override
    public List<Airline> findByLuggageDetailsMandatory(String luggageDetails) {
        log.trace(".findByLuggageDetailsMandatory(luggageDetails: {})", luggageDetails);
        final QAirline airline = QAirline.airline;
        return factory.select(airline).from(airline).where(airline.luggageDetails.eq(luggageDetails)).fetch();
    }

    @Override
    public List<Airline> airlines() {
        log.trace(".airlines()");
        final QAirline airline = QAirline.airline;
        return factory.select(airline).from(airline).fetch();
    }

    @Override
    public Optional<Airline> readAirline(Long id) {
        log.trace(".readAirline(id: {})", id);
        final QAirline airline = QAirline.airline;
        return Optional.ofNullable(factory.select(airline).from(airline).where(airline.id.eq(id)).fetchOne());
    }

}
