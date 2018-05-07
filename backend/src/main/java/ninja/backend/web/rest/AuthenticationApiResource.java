package ninja.backend.web.rest;

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
import ninja.backend.service.*;

import ninja.backend.model.*;
import ninja.backend.api.dto.*;
import ninja.backend.model.enumeration.*;
import org.springframework.format.annotation.DateTimeFormat;


@RestController
@RequestMapping("/api/")
public class AuthenticationApiResource {

    private final Logger log = LoggerFactory.getLogger(AuthenticationApiResource.class);

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationApi authenticationApi;

    @RequestMapping(value = "/refresh-token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        log.debug("POST /refresh-token {}", request);

        final SignInResponse response = userService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        log.debug("POST /sign-up {}", request);

        final User user = userService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request) {
        log.debug("POST /sign-in {}", request);

        final Optional<SignInResponse> response = userService.signIn(request.getUsername(), request.getPassword());
        if (response.isPresent())
            return ResponseEntity.ok().body(response.get());
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /change-password {}", request);

        final User user = userService.changePassword(principalId, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
