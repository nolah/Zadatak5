package ninja.backend.repository.impl;

import java.time.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.UserRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public Optional<User> findByIdWithPerson(Long id) {
        log.trace(".findByIdWithPerson(id: {})", id);
        final QUser user = QUser.user;
        return Optional.ofNullable(factory.select(user).from(user).where(user.id.eq(id)).fetchOne());
    }

    @Override
    public List<User> findBySetPasswordCode(Optional<String> setPasswordCode) {
        log.trace(".findBySetPasswordCode(setPasswordCode)");
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(setPasswordCode.map(user.setPasswordCode::eq).orElse(null)).fetch();
    }

    @Override
    public Optional<User> findBySetPasswordCodeMandatory(String setPasswordCode) {
        log.trace(".findBySetPasswordCodeMandatory(setPasswordCode)");
        final QUser user = QUser.user;
        return Optional.ofNullable(factory.select(user).from(user).where(user.setPasswordCode.eq(setPasswordCode)).fetchOne());
    }

    @Override
    public List<User> findBySetPasswordTimestamp(ZonedDateTime setPasswordTimestamp) {
        log.trace(".findBySetPasswordTimestamp(setPasswordTimestamp: {})", setPasswordTimestamp);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.setPasswordTimestamp.eq(setPasswordTimestamp)).fetch();
    }

    @Override
    public List<User> findByRole(Role role) {
        log.trace(".findByRole(role: {})", role);
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.role.eq(role)).fetch();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.trace(".findByUsername(username: {})", username);
        final QUser user = QUser.user;
        return Optional.ofNullable(factory.select(user).from(user).where(user.username.eq(username)).fetchOne());
    }

    @Override
    public List<User> findByPasswordHash(String passwordHash) {
        log.trace(".findByPasswordHash(passwordHash)");
        final QUser user = QUser.user;
        return factory.select(user).from(user).where(user.passwordHash.eq(passwordHash)).fetch();
    }

}
