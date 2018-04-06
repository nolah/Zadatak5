package ninja.backend.service;

import java.time.*;
import org.apache.commons.lang3.RandomStringUtils;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import java.util.*;

import ninja.backend.config.CustomProperties;
import ninja.backend.security.JWTService;
import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;
import ninja.backend.web.rest.exception.*;


@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private CustomProperties customProperties;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private JWTService jwtService;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public User signUp(SignUpRequest dto) {
        log.debug("signUp(dto)", dto);

        final User model = new User();
        model.setSetPasswordCode(Optional.ofNullable(dto.getSetPasswordCode()));
        model.setSetPasswordTimestamp(dto.getSetPasswordTimestamp());
        model.setUsername(dto.getUsername());
        // model.setPassword(); // TODO set this field manually
        model.setRole(Role.USER);
        model.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(model);
        return model;
    }

    @Transactional
    public Optional<SignInResponse> signIn(String username, String password) {
        log.debug("signIn(username: {})", username);

        final Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent())
            return Optional.empty();
        if (!passwordEncoder.matches(password, user.get().getPasswordHash()))
            return Optional.empty();

        final User result = userRepository.findByIdWithPerson(user.get().getId()).get();
        return Optional.of(signInResponse(result, Optional.empty()));
    }

    @Transactional
    public User changePassword(Long userId, String oldPassword, String newPassword) {

        log.debug("changePassword(userId: {})", userId);

        final User model = userRepository.findOne(userId);
        if (model == null) {
            throw new BadRequestError("credentials.invalid", "Credentials are invalid!");
        }
        if (!passwordEncoder.matches(oldPassword, model.getPasswordHash())) {
            throw new BadRequestError("credentials.invalid", "Credentials are invalid!");
        }
        model.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(model);
        return model;
    }

    @Transactional
    public SignInResponse refreshToken(String refreshToken) {

        log.debug("refreshToken(refreshToken: {})", refreshToken);

        final Claims claims = jwtService.getJwtClaims(refreshToken);
        if (claims.getExpiration().before(new Date())) {
            throw new BadRequestError("refreshToken.expired", "Refresh token has expired.");
        }

        final User result = userRepository.findByIdWithPerson(Long.valueOf(claims.getSubject())).get();
        return signInResponse(result, Optional.empty());
    }

    private SignInResponse signInResponse(User model, Optional<String> oldRefreshToken) {

        final String accessToken = jwtService.createAccessToken(model.getId(), model.getRole());
        final String refreshToken = oldRefreshToken.orElse(jwtService.createRefreshToken(model.getId()));

        final Long id = model.getId();
        final String username = model.getUsername();
        final Role role = model.getRole();
        return new SignInResponse(accessToken, refreshToken, id, username, role);
    }
}
