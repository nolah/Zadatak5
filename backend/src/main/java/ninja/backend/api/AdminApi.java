package ninja.backend.api;

import java.time.*;

import javax.inject.Inject;

import ninja.backend.web.rest.exception.BadRequestError;
import org.joda.time.DateTime;
import org.slf4j.*;

import ninja.backend.model.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;

import java.util.*;
import java.util.stream.*;

import ninja.backend.model.enumeration.*;

import java.math.BigDecimal;

import ninja.backend.repository.tuple.*;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Inject
    private FlightRepository flightRepository;

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

    @Transactional(readOnly = true)
    public List<FlightsResponse> flights(Long principalId) {
        log.debug("flights {}", principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        final List<FlightFlightsTuple> tuples = flightRepository.flights();
        return tuples.stream().map(tuple -> {
            final Long id = tuple.getFlight().getId();
            final Long aircraftId = tuple.getFlight().getAircraft().getId();
            final ZonedDateTime timestamp = tuple.getFlight().getTimestamp();
            final Integer numberOfEconomySeats = tuple.getFlight().getNumberOfEconomySeats();
            final Integer freeEconomySeats = tuple.getFlight().getFreeEconomySeats();
            final BigDecimal priceOfEconomySeat = tuple.getFlight().getPriceOfEconomySeat();
            final Integer numberOfBusinessSeats = tuple.getFlight().getNumberOfBusinessSeats();
            final Integer freeBusinessSeats = tuple.getFlight().getFreeBusinessSeats();
            final BigDecimal priceOfBusinessSeats = tuple.getFlight().getPriceOfBusinessSeats();
            final String fromAirport = tuple.getFlight().getFromAirport();
            final String toAirport = tuple.getFlight().getToAirport();
            final String aircraftMaker = tuple.getAircraft().getMaker();
            final String aircraftType = tuple.getAircraft().getType();
            final Long aircraftAirlineId = tuple.getAircraft().getAirline().getId();
            final Long airlineId = tuple.getAirline().getId();
            final String airlineName = tuple.getAirline().getName();
            final String airlineDescription = tuple.getAirline().getDescription();
            final String airlineLuggageDetails = tuple.getAirline().getLuggageDetails().orElse(null);
            return new FlightsResponse(id, aircraftId, timestamp, numberOfEconomySeats, freeEconomySeats, priceOfEconomySeat, numberOfBusinessSeats, freeBusinessSeats, priceOfBusinessSeats,
                    fromAirport, toAirport, aircraftMaker, aircraftType, aircraftAirlineId, airlineId, airlineName, airlineDescription, airlineLuggageDetails);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReadFlightResponse readFlight(ReadFlightRequest dto, Long principalId) {
        log.debug("readFlight {} {}", dto, principalId);
        //TODO check security constraints(id)

        final User principal = userRepository.findOne(principalId);

        final Flight model = flightRepository.readFlight(dto.getId()).get();
        final Long id = model.getId();
        final Long aircraftId = model.getAircraft().getId();
        final ZonedDateTime timestamp = model.getTimestamp();
        final Integer numberOfEconomySeats = model.getNumberOfEconomySeats();
        final Integer freeEconomySeats = model.getFreeEconomySeats();
        final BigDecimal priceOfEconomySeat = model.getPriceOfEconomySeat();
        final Integer numberOfBusinessSeats = model.getNumberOfBusinessSeats();
        final Integer freeBusinessSeats = model.getFreeBusinessSeats();
        final BigDecimal priceOfBusinessSeats = model.getPriceOfBusinessSeats();
        final String fromAirport = model.getFromAirport();
        final String toAirport = model.getToAirport();
        return new ReadFlightResponse(id, aircraftId, timestamp, numberOfEconomySeats, freeEconomySeats, priceOfEconomySeat, numberOfBusinessSeats, freeBusinessSeats, priceOfBusinessSeats,
                fromAirport, toAirport);
    }

    public void createFlights(CreateFlightsRequest dto, Long principalId) {
        log.debug("createFlights {} {}", dto, principalId);
        //TODO check security constraints(aircraftId)

        if (dto.getSchemeType() == FlightSchemeType.ONE_OFF) {
            saveFlight(dto, dto.getFromDate());
        } else if (dto.getSchemeType() == FlightSchemeType.WORK_DAYS) {
            if (dto.getToDate() == null) {
                throw new BadRequestError("toDate.missing", "toDate is required for scheme type: " + dto.getSchemeType());
            }

            ZonedDateTime shiftedDate = dto.getFromDate();
            while (shiftedDate.isBefore(dto.getToDate())) {
                if (shiftedDate.getDayOfWeek() != DayOfWeek.SATURDAY && shiftedDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    saveFlight(dto, shiftedDate);
                    shiftedDate = shiftedDate.plusDays(1);
                } else if (shiftedDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    shiftedDate = shiftedDate.plusDays(2);
                } else if (shiftedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    shiftedDate = shiftedDate.plusDays(1);
                }
            }

        } else if (dto.getSchemeType() == FlightSchemeType.EVERYDAY) {
            ZonedDateTime shiftedDate = dto.getFromDate();
            while (shiftedDate.isBefore(dto.getToDate())) {
                saveFlight(dto, shiftedDate);
                shiftedDate = shiftedDate.plusDays(1);
            }

        }else{
            throw new IllegalStateException("Unsupported scheme type: " + dto.getSchemeType());
        }
    }

    private void saveFlight(CreateFlightsRequest dto, ZonedDateTime timestamp) {
        final Flight model = new Flight();
        model.setAircraft(aircraftRepository.findOne(dto.getAircraftId()));
        model.setTimestamp(timestamp);
        model.setNumberOfEconomySeats(dto.getNumberOfEconomySeats());
        model.setPriceOfEconomySeat(dto.getPriceOfEconomySeat());
        model.setFreeEconomySeats(dto.getNumberOfEconomySeats());
        model.setNumberOfBusinessSeats(dto.getNumberOfBusinessSeats());
        model.setPriceOfBusinessSeats(dto.getPriceOfBusinessSeats());
        model.setFreeBusinessSeats(dto.getNumberOfBusinessSeats());
        model.setFromAirport(dto.getFromAirport());
        model.setToAirport(dto.getToAirport());
        // model.setSchemeType(); // TODO set this field manually
        flightRepository.save(model);
    }

    public void updateFlight(UpdateFlightRequest dto, Long principalId) {
        log.debug("updateFlight {} {}", dto, principalId);
        //TODO check security constraints(id, aircraftId)

        final User principal = userRepository.findOne(principalId);

        final Flight model = flightRepository.findOne(dto.getId());
        model.setAircraft(aircraftRepository.findOne(dto.getAircraftId()));
        model.setTimestamp(dto.getTimestamp());
        model.setNumberOfEconomySeats(dto.getNumberOfEconomySeats());
        model.setPriceOfEconomySeat(dto.getPriceOfEconomySeat());
        model.setNumberOfBusinessSeats(dto.getNumberOfBusinessSeats());
        model.setPriceOfBusinessSeats(dto.getPriceOfBusinessSeats());
        model.setFromAirport(dto.getFromAirport());
        model.setToAirport(dto.getToAirport());
        flightRepository.save(model);
        //TODO process event

    }

}
