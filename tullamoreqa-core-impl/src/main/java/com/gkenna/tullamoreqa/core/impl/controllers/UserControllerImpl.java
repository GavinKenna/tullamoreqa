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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserControllerImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> addUser(User input) {
        return null;
    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId, User input) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return null;
    }
}
