/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.UserController;
import com.gkenna.tullamoreqa.core.api.exceptions.UserNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Implementation of {@link UserController}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    /**
     * User Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserControllerImpl.class);

    /**
     * User Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the User Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.UserRepository}.
     */
    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<?> addUser(@RequestBody final User input) {
        LOGGER.debug("Adding User {}", input);

        //TODO Add exception handling
        userService.addUser(input);
        /*
        Retrieving URI of new User.
         */
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(input.getUsername()).toUri();

        LOGGER.debug("User #{} URI location is {}", input.getUsername(),
                location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public final ResponseEntity<User> getUser(
            @PathVariable("username") final String username) {

        LOGGER.debug("Attempting to get User {}", username);
        User output = null;
        try {
            output = userService.getUser(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (output == null) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("User with username "
                    + username + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{username}")
    public final ResponseEntity<?> updateUser(
            @PathVariable("username") final String username,
            @RequestBody final User input) {

        LOGGER.debug("Updating User {} with the following details {}",
                username, input);

        User output;
        try {
            output = userService.updateUser(username, input);
        } catch (UserNotFoundException e) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with username "
                    + username + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public final ResponseEntity<?> deleteUser(
            @PathVariable("username") final String username) {

        LOGGER.debug("Deleting User {}", username);
        User output;
        try {
            output = userService.deleteUser(username);
        } catch (UserNotFoundException e) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("User with username "
                    + username + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }
}
