/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.UserController;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserControllerImpl.class);

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User input) {
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

        LOGGER.debug("User #{} URI location is {}", input.getUsername(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        LOGGER.debug("Attempting to get User {}", username);
        User output = userService.getUser(username);
        if (output == null) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{username}")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User input) {
        LOGGER.debug("Updating User {} with the following details {}", username, input);
        User output;
        try {
            output = userService.updateUser(username, input);
        } catch (Exception e) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        LOGGER.debug("Deleting User {}", username);
        User output;
        try {
            output = userService.deleteUser(username);
        } catch (Exception e) {
            LOGGER.error("User with username {} not found.", username);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("User with username " + username
                    + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(output, HttpStatus.OK);
    }
}
