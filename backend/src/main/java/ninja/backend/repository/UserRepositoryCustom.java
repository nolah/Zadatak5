package ninja.backend.repository;

import java.time.*;
import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;


public interface UserRepositoryCustom {

    Optional<User> findByIdWithPerson(Long id);

    List<User> findBySetPasswordCode(Optional<String> setPasswordCode);

    Optional<User> findBySetPasswordCodeMandatory(String setPasswordCode);

    List<User> findBySetPasswordTimestamp(ZonedDateTime setPasswordTimestamp);

    List<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    List<User> findByPasswordHash(String passwordHash);

}
