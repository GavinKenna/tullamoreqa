/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.User;
import org.springframework.http.ResponseEntity;

/**
 * API Controller for the {@link User} Entity. This API will allow
 * external parties, i.e. UI or CLI, to Get/Add/Update/Delete Users.
 * <p>
 * The Implementation of this API will in turn call the
 * {@link com.gkenna.tullamoreqa.core.api.services.UserService} API.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface UserController {
    /**
     * HTTP POST Method
     * <p>
     * Add an {@link User} to the Database.
     *
     * @param input An {@link User} container that should be inserted into
     *              the Database.
     * @return The Response of this Request.
     */
    ResponseEntity<?> addUser(final User input);

    /**
     * HTTP GET Method
     * <p>
     * Retrieve an {@link User} from the Database.
     *
     * @param username The ID of the {@link User} to retrieve.
     * @return The Response of this Request.
     */
    ResponseEntity<User> getUser(final String username);

    /**
     * HTTP PUT Method
     * <p>
     * Update an {@link User} on the Database.
     *
     * @param username The ID of the {@link User} to update.
     * @param input    An {@link User} container that holds
     *                 new values for userId to update to.
     * @return The Response of this Request.
     */
    ResponseEntity<?> updateUser(final String username, final User input);

    /**
     * HTTP DELETE Method.
     * <p>
     * Delete an {@link User} from the Database.
     *
     * @param username The ID of the {@link User} to delete.
     * @return The Response of this Request.
     */
    ResponseEntity<?> deleteUser(final String username);
}
