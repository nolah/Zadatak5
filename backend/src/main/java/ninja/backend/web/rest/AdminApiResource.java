package ninja.backend.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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


@RestController
@RequestMapping("/api/")
public class AdminApiResource {

    private final Logger log = LoggerFactory.getLogger(AdminApiResource.class);

    @Inject
    private AdminApi adminApi;

    @RequestMapping(value = "/aircrafts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AircraftsResponse>> aircrafts(@ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("GET /aircrafts");

        final List<AircraftsResponse> response = adminApi.aircrafts(principalId);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/read-aircraft", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReadAircraftResponse> readAircraft(@RequestParam("id") Long id, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("GET /read-aircraft");

        final ReadAircraftRequest request = new ReadAircraftRequest(id);
        final ReadAircraftResponse response = adminApi.readAircraft(request, principalId);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/create-aircraft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> createAircraft(@Valid @RequestBody CreateAircraftRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /create-aircraft {}", request);

        adminApi.createAircraft(request, principalId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update-aircraft", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateAircraft(@Valid @RequestBody UpdateAircraftRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("PUT /update-aircraft {}", request);

        adminApi.updateAircraft(request, principalId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/airlines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AirlinesResponse>> airlines(@ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("GET /airlines");

        final List<AirlinesResponse> response = adminApi.airlines(principalId);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/read-airline", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReadAirlineResponse> readAirline(@RequestParam("id") Long id, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("GET /read-airline");

        final ReadAirlineRequest request = new ReadAirlineRequest(id);
        final ReadAirlineResponse response = adminApi.readAirline(request, principalId);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/create-airline", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> createAirline(@Valid @RequestBody CreateAirlineRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /create-airline {}", request);

        adminApi.createAirline(request, principalId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update-airline", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateAirline(@Valid @RequestBody UpdateAirlineRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("PUT /update-airline {}", request);

        adminApi.updateAirline(request, principalId);
        return ResponseEntity.ok().build();
    }
}
