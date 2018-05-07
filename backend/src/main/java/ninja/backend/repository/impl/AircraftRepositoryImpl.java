package ninja.backend.repository.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
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
    public List<Aircraft> findByAirline(Long airlineId) {
        log.trace(".findByAirline(airlineId: {})", airlineId);
        final QAircraft aircraft = QAircraft.aircraft;
        return factory.select(aircraft).from(aircraft).where(aircraft.airline.id.eq(airlineId)).fetch();
    }

    @Override
    public List<AircraftAircraftsTuple> aircrafts() {
        log.trace(".aircrafts()");
        final QAircraft aircraft = QAircraft.aircraft;
        final QAirline airline = QAirline.airline;
        return factory.select(aircraft, airline).from(aircraft).innerJoin(aircraft.airline, airline).fetch().stream().map(t -> new AircraftAircraftsTuple(t.get(aircraft), t.get(airline)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Aircraft> readAircraft(Long id) {
        log.trace(".readAircraft(id: {})", id);
        final QAircraft aircraft = QAircraft.aircraft;
        return Optional.ofNullable(factory.select(aircraft).from(aircraft).where(aircraft.id.eq(id)).fetchOne());
    }

}
