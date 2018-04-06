package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.AircraftRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class AircraftRepositoryImpl implements AircraftRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(AircraftRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Aircraft> findByMaker(String maker) {
        log.trace(".findByMaker(maker: {})", maker);
        final QAircraft aircraft = QAircraft.aircraft;
        return factory.select(aircraft).from(aircraft).where(aircraft.maker.eq(maker)).fetch();
    }

    @Override
    public List<Aircraft> findByType(String type) {
        log.trace(".findByType(type: {})", type);
        final QAircraft aircraft = QAircraft.aircraft;
        return factory.select(aircraft).from(aircraft).where(aircraft.type.eq(type)).fetch();
    }

    @Override
    public List<Aircraft> aircrafts() {
        log.trace(".aircrafts()");
        final QAircraft aircraft = QAircraft.aircraft;
        return factory.select(aircraft).from(aircraft).fetch();
    }

    @Override
    public Optional<Aircraft> readAircraft(Long id) {
        log.trace(".readAircraft(id: {})", id);
        final QAircraft aircraft = QAircraft.aircraft;
        return Optional.ofNullable(factory.select(aircraft).from(aircraft).where(aircraft.id.eq(id)).fetchOne());
    }

}
