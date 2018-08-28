/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.UserNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService}.
 *
 * @author Gavin Kenna
 * @see UserService
 * @since 0.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * User Service Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserServiceImpl.class);

    /**
     * User Repository, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the DB.
     * We will use this object to Add/Delete/Update/Get {@link User}.
     */
    private final UserRepository userRepository;

    /**
     * Constructor that Auto wires the User Repository.
     *
     * @param userRepository UserRepo object.
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public final void addUser(final User user) {
        LOGGER.debug("Adding New User {}", user);
        userRepository.save(user);
        LOGGER.debug("New User with ID {} added successfully.",
                user.getUsername());
    }

    @Override
    public final void deleteUser(final User user) {
    }

    @Override
    public final User deleteUser(final String id) {
        return null;
    }

    @Override
    public final User updateUser(final String username, final User input) {
        return null;
    }

    @Override
    public final boolean doesUserExist(final User user) {
        return false;
    }

    @Override
    public final boolean doesUserExist(final String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    @Override
    public final User getUser(final String id) {
        return null;
    }

    @Override
    public final List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User patchUser(String username, User input) throws UserNotFoundException {
        return null;
    }
}
