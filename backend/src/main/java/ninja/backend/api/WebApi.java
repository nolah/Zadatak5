package ninja.backend.api;

import java.time.*;

import javax.inject.Inject;

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

import static java.util.Optional.empty;
import static java.util.Optional.of;


@Service
@Transactional
public class WebApi {

    private final Logger log = LoggerFactory.getLogger(WebApi.class);

    @Inject
    private FlightRepository flightRepository;

    @Inject
    private FlightReservationRepository flightReservationRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public SearchFlightsResponse searchFlights(SearchFlightsRequestDto dto) {
        log.debug("searchFlights {}", dto);


        final ZonedDateTime startOfDepartingDate = dto.getDepartingDate().toLocalDate().atStartOfDay(dto.getDepartingDate().getZone());
        final ZonedDateTime endOfDepartingDate = dto.getDepartingDate().plusDays(1).toLocalDate().atStartOfDay(dto.getDepartingDate().getZone());
        final List<FlightSearchFlightsTuple> departingFlights = flightRepository.searchFlights(startOfDepartingDate, endOfDepartingDate, dto.getFromAirport(), dto.getToAirport());

        final ZonedDateTime startOfReturningDate = dto.getReturning().toLocalDate().atStartOfDay(dto.getReturning().getZone());
        final ZonedDateTime endOfReturningDate = dto.getReturning().plusDays(1).toLocalDate().atStartOfDay(dto.getReturning().getZone());
        final List<FlightSearchFlightsTuple> returningFlights = flightRepository.searchFlights(startOfReturningDate, endOfReturningDate, dto.getToAirport(), dto.getFromAirport());


        final List<SearchFlightDto> departingFlightsDtos = departingFlights.stream().map(tuple -> new SearchFlightDto(
                tuple.getFlight().getId(),
                tuple.getFlight().getTimestamp(),
                tuple.getFlight().getNumberOfEconomySeats(),
                tuple.getFlight().getFreeEconomySeats(),
                tuple.getFlight().getPriceOfEconomySeat(),
                tuple.getFlight().getNumberOfBusinessSeats(),
                tuple.getFlight().getFreeBusinessSeats(),
                tuple.getFlight().getPriceOfBusinessSeats(),
                tuple.getFlight().getFromAirport(),
                tuple.getFlight().getToAirport(),
                tuple.getAircraft().getMaker(),
                tuple.getAircraft().getType(),
                tuple.getAirline().getName(),
                true
        )).collect(Collectors.toList());

        final List<SearchFlightDto> returningFlightsDtos = returningFlights.stream().map(tuple -> new SearchFlightDto(
                tuple.getFlight().getId(),
                tuple.getFlight().getTimestamp(),
                tuple.getFlight().getNumberOfEconomySeats(),
                tuple.getFlight().getFreeEconomySeats(),
                tuple.getFlight().getPriceOfEconomySeat(),
                tuple.getFlight().getNumberOfBusinessSeats(),
                tuple.getFlight().getFreeBusinessSeats(),
                tuple.getFlight().getPriceOfBusinessSeats(),
                tuple.getFlight().getFromAirport(),
                tuple.getFlight().getToAirport(),
                tuple.getAircraft().getMaker(),
                tuple.getAircraft().getType(),
                tuple.getAirline().getName(),
                true
        )).collect(Collectors.toList());

        return new SearchFlightsResponse(departingFlightsDtos, returningFlightsDtos);
    }

    public void bookFlight(BookFlightRequest dto, Long principalId) {
        log.debug("bookFlight {} {}", dto, principalId);

        final User principal = userRepository.findOne(principalId);

        final FlightReservation flightReservation = new FlightReservation();
        flightReservation.setUser(principal);
        flightReservation.setPaymentMethod(dto.getPaymentMethod());

        final List<FlightPassenger> flightPassengers = dto.getPassengers().stream().map(passengerDto -> new FlightPassenger(
                flightReservation,
                passengerDto.getFirstName(),
                passengerDto.getLastName(),
                passengerDto.getPassportNumber(),
                passengerDto.getSmallCabinLuggage(),
                passengerDto.getLargeCabinLuggage(),
                passengerDto.getCheckedBag(),
                passengerDto.getSeatType()
        )).collect(Collectors.toList());

        flightReservation.getFlightPassengers().addAll(flightPassengers);

        final Integer numberOfEconomySeats = flightPassengers.stream().filter(flightPassenger -> flightPassenger.getSeatType().equals(SeatType.ECONOMY)).collect(Collectors.toList()).size();
        final Integer numberOfBusinessSeats = flightPassengers.stream().filter(flightPassenger -> flightPassenger.getSeatType().equals(SeatType.BUSINESS)).collect(Collectors.toList()).size();


        if (dto.getDepartingFlight() != null) {
            final Flight departingFlight = flightRepository.findOne(dto.getDepartingFlight());
            flightReservation.setDepartingFlight(of(departingFlight));

            // update flight
            departingFlight.setFreeEconomySeats(departingFlight.getFreeEconomySeats() - numberOfEconomySeats);
            departingFlight.setFreeBusinessSeats(departingFlight.getFreeBusinessSeats() - numberOfBusinessSeats);
            flightRepository.save(departingFlight);
        }

        if(dto.getReturningFlight() != null){
            final Flight returningFlight = flightRepository.findOne(dto.getReturningFlight());
            flightReservation.setReturningFlight(of(returningFlight));

            // update flight
            returningFlight.setFreeEconomySeats(returningFlight.getFreeEconomySeats() - numberOfEconomySeats);
            returningFlight.setFreeBusinessSeats(returningFlight.getFreeBusinessSeats() - numberOfBusinessSeats);
            flightRepository.save(returningFlight);
        }

        flightReservationRepository.save(flightReservation);
    }

}
