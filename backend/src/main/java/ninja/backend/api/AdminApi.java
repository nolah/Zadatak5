package ninja.backend.api;

import javax.inject.Inject;

import org.slf4j.*;

import ninja.backend.model.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;

import java.util.*;
import java.util.stream.*;
import ninja.backend.model.enumeration.*;

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

    @Transactional(readOnly = true)
    public List<AircraftsResponse> aircrafts(Long principalId) {
        log.debug("aircrafts {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final List<Aircraft> models = aircraftRepository.aircrafts();
        return models.stream().map(model -> {
            final Long id = model.getId();
            final String maker = model.getMaker();
            final String type = model.getType();
            return new AircraftsResponse(id, maker, type);
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
        return new ReadAircraftResponse(id, maker, type);
    }

    public void createAircraft(CreateAircraftRequest dto, Long principalId) {
        log.debug("createAircraft {} {}", dto, principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final Aircraft model = new Aircraft();
        model.setMaker(dto.getMaker());
        model.setType(dto.getType());
        aircraftRepository.save(model);
        //TODO process event

    }

    public void updateAircraft(UpdateAircraftRequest dto, Long principalId) {
        log.debug("updateAircraft {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Aircraft model = aircraftRepository.findOne(dto.getId());
        model.setMaker(dto.getMaker());
        model.setType(dto.getType());
        aircraftRepository.save(model);
        //TODO process event

    }

}
