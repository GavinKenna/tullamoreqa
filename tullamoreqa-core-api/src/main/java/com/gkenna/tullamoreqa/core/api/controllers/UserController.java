/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.User;
import org.springframework.http.ResponseEntity;

public interface UserController {
    /**
     * POST Method
     *
     * @param input
     * @return
     */
    ResponseEntity<?> addUser(User input);

    /**
     * GET Method
     *
     * @param username
     * @return
     */
    ResponseEntity<User> getUser(String username);

    /**
     * PUT Method
     * @param username
     * @param input
     * @return
     */
    ResponseEntity<?> updateUser(String username, User input);


    /**
     * DELETE Method
     * @param username
     * @return
     */
    ResponseEntity<?> deleteUser(String username);
}
