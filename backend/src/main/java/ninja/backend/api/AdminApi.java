package ninja.backend.api;

import javax.inject.Inject;

import org.slf4j.*;

import ninja.backend.model.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;

import java.util.*;
import java.util.stream.*;
import ninja.backend.model.enumeration.*;

import ninja.backend.repository.tuple.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminApi {

    private final Logger log = LoggerFactory.getLogger(AdminApi.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private AircraftRepository aircraftRepository;

    @Inject
    private AirlineRepository airlineRepository;

    @Transactional(readOnly = true)
    public List<AircraftsResponse> aircrafts(Long principalId) {
        log.debug("aircrafts {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final List<AircraftAircraftsTuple> tuples = aircraftRepository.aircrafts();
        return tuples.stream().map(tuple -> {
            final Long id = tuple.getAircraft().getId();
            final String maker = tuple.getAircraft().getMaker();
            final String type = tuple.getAircraft().getType();
            final Long airlineId = tuple.getAircraft().getAirline().getId();
            final String airlineName = tuple.getAirline().getName();
            final String airlineDescription = tuple.getAirline().getDescription();
            final String airlineLuggageDetails = tuple.getAirline().getLuggageDetails().orElse(null);
            return new AircraftsResponse(id, maker, type, airlineId, airlineName, airlineDescription, airlineLuggageDetails);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReadAircraftResponse readAircraft(ReadAircraftRequest dto, Long principalId) {
        log.debug("readAircraft {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Aircraft model = aircraftRepository.readAircraft(dto.getId()).get();
        final Long id = model.getId();
        final String maker = model.getMaker();
        final String type = model.getType();
        final Long airlineId = model.getAirline().getId();
        return new ReadAircraftResponse(id, maker, type, airlineId);
    }

    public void createAircraft(CreateAircraftRequest dto, Long principalId) {
        log.debug("createAircraft {} {}", dto, principalId);
        //TODO check security constraints(airlineId)

        final User principal = userRepository.findOne(principalId);

        final Aircraft model = new Aircraft();
        model.setMaker(dto.getMaker());
        model.setType(dto.getType());
        model.setAirline(airlineRepository.findOne(dto.getAirlineId()));
        aircraftRepository.save(model);
        //TODO process event

    }

    public void updateAircraft(UpdateAircraftRequest dto, Long principalId) {
        log.debug("updateAircraft {} {}", dto, principalId);
        //TODO check security constraints(id, airlineId)

        final User principal = userRepository.findOne(principalId);

        final Aircraft model = aircraftRepository.findOne(dto.getId());
        model.setMaker(dto.getMaker());
        model.setType(dto.getType());
        model.setAirline(airlineRepository.findOne(dto.getAirlineId()));
        aircraftRepository.save(model);
        //TODO process event

    }

    @Transactional(readOnly = true)
    public List<AirlinesResponse> airlines(Long principalId) {
        log.debug("airlines {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final List<Airline> models = airlineRepository.airlines();
        return models.stream().map(model -> {
            final Long id = model.getId();
            final String name = model.getName();
            final String description = model.getDescription();
            final String luggageDetails = model.getLuggageDetails().orElse(null);
            return new AirlinesResponse(id, name, description, luggageDetails);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReadAirlineResponse readAirline(ReadAirlineRequest dto, Long principalId) {
        log.debug("readAirline {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Airline model = airlineRepository.readAirline(dto.getId()).get();
        final Long id = model.getId();
        final String name = model.getName();
        final String description = model.getDescription();
        final String luggageDetails = model.getLuggageDetails().orElse(null);
        return new ReadAirlineResponse(id, name, description, luggageDetails);
    }

    public void createAirline(CreateAirlineRequest dto, Long principalId) {
        log.debug("createAirline {} {}", dto, principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final Airline model = new Airline();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setLuggageDetails(Optional.ofNullable(dto.getLuggageDetails()));
        airlineRepository.save(model);
        //TODO process event

    }

    public void updateAirline(UpdateAirlineRequest dto, Long principalId) {
        log.debug("updateAirline {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Airline model = airlineRepository.findOne(dto.getId());
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setLuggageDetails(Optional.ofNullable(dto.getLuggageDetails()));
        airlineRepository.save(model);
        //TODO process event

    }

}
