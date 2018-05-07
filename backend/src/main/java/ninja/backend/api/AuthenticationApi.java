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

import ninja.backend.repository.tuple.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthenticationApi {

    private final Logger log = LoggerFactory.getLogger(AuthenticationApi.class);

    @Inject
    private UserRepository userRepository;

    public SignInResponse refreshToken(RefreshTokenRequest dto) {
        log.debug("refreshToken {}", dto);

        //TODO process event
        throw new UnsupportedOperationException();
    }

    public void signUp(SignUpRequest dto) {
        log.debug("signUp {}", dto);

        //TODO process event
        throw new UnsupportedOperationException();
    }

    public SignInResponse signIn(SignInRequest dto) {
        log.debug("signIn {}", dto);

        //TODO process event
        throw new UnsupportedOperationException();
    }

    public void changePassword(ChangePasswordRequest dto, Long principalId) {
        log.debug("changePassword {} {}", dto, principalId);
        //TODO check security constraints

        final User principal = userRepository.findOne(principalId);

        //TODO process event
        throw new UnsupportedOperationException();
    }

}
