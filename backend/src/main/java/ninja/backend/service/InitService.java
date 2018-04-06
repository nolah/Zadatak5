package ninja.backend.service;

import ninja.backend.api.AuthenticationApi;
import ninja.backend.api.dto.SignUpRequest;
import ninja.backend.model.User;
import ninja.backend.model.enumeration.Role;
import ninja.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Created by olahnikola on 6.4.18..
 */

@Service
public class InitService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        final Optional<User> admin = userRepository.findByUsername("admin");
        if (!admin.isPresent()) {
            final User model = new User();
            model.setSetPasswordCode(Optional.empty());
            model.setSetPasswordTimestamp(ZonedDateTime.now());
            model.setUsername("admin");
            // model.setPassword(); // TODO set this field manually
            model.setRole(Role.ADMIN);
            model.setPasswordHash(passwordEncoder.encode("Lozinka1234"));
            userRepository.save(model);
        }
    }
}
