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
     * @param userId
     * @return
     */
    User getUser(Long userId);

    /**
     * PUT Method
     * @param userId
     * @param input
     * @return
     */
    ResponseEntity<?> updateUser(Long userId, User input);


    /**
     * DELETE Method
     * @param userId
     * @return
     */
    ResponseEntity<?> deleteUser(Long userId);
}
