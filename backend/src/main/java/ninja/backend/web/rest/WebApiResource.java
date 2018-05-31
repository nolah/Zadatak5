package ninja.backend.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.*;
import java.util.Optional;

import javax.inject.Inject;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import ninja.backend.api.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.model.*;
import ninja.backend.api.dto.*;
import ninja.backend.model.enumeration.*;
import org.springframework.format.annotation.DateTimeFormat;


@RestController
@RequestMapping("/api/")
public class WebApiResource {

    private final Logger log = LoggerFactory.getLogger(WebApiResource.class);

    @Inject
    private WebApi webApi;

    @RequestMapping(value = "/search-flights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchFlightsResponse> searchFlights(@RequestParam("departingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime departingDate,
            @RequestParam("returning") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime returning, @RequestParam("fromAirport") String fromAirport,
            @RequestParam("toAirport") String toAirport, @RequestParam("travelers") Integer travelers, @RequestParam("seatType") SeatType seatType) {
        log.debug("GET /search-flights");

        final SearchFlightsRequestDto request = new SearchFlightsRequestDto(departingDate, returning, fromAirport, toAirport, travelers, seatType);
        final SearchFlightsResponse response = webApi.searchFlights(request);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/book-flight", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> bookFlight(@Valid @RequestBody BookFlightRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /book-flight {}", request);

        webApi.bookFlight(request, principalId);
        return ResponseEntity.ok().build();
    }
}
