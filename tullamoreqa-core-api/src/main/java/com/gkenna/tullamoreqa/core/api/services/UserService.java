/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.UserNotFoundException;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.User}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.UserRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface UserService {
    /**
     * Insert a new {@link User} to the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.UserRepository}.
     *
     * @param user {@link User} to Add.
     */
    void addUser(final User user);

    /**
     * Delete an {@link User} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.UserRepository}.
     *
     * @param user {@link User} to Delete.
     * @throws UserNotFoundException Thrown if {@link User}
     *                               cannot be found.
     */
    void deleteUser(final User user) throws UserNotFoundException;

    /**
     * Delete a {@link User} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.UserRepository}.
     *
     * @param username ID of the {@link User} to delete.
     * @return Deleted User.
     * @throws UserNotFoundException Thrown if {@link User}
     *                               cannot be found.
     */
    User deleteUser(final String username) throws UserNotFoundException;

    /**
     * Update a {@link User} on the Database.
     *
     * @param username The ID of the {@link User} to update.
     * @param input    A {@link User} container that holds
     *                 new values for questionId to update to.
     * @return The {@link User} that was updated.
     * @throws UserNotFoundException Thrown when the {@link User} cannot
     *                               be found.
     */
    User updateUser(final String username, final User input)
            throws UserNotFoundException;

    /**
     * Return if a {@link User} exists in the DB or not.
     *
     * @param user Does this {@link User} exist?
     * @return True if the {@link User} exists,
     * false if otherwise.
     */
    boolean doesUserExist(final User user);

    /**
     * Return if a {@link User} exists in the DB or not.
     *
     * @param username Does the ID for this {@link User} exist?
     * @return True if the {@link User} exists,
     * false if otherwise.
     */
    boolean doesUserExist(final String username);

    /**
     * Return an {@link User} based on its ID.
     *
     * @param username The ID of the {@link User} to return.
     * @return A {@link User} with the
     * ID passed.
     * @throws UserNotFoundException Thrown if the {@link User} cannot be found.
     */
    User getUser(final String username) throws UserNotFoundException;

    /**
     * Return all {@link User}s in the DB.
     *
     * @return All {@link User}s in the DB.
     */
    User[] getAllUsers();

    /**
     * Patch a {@link User} on the Database.
     *
     * @param username The username of the {@link User} to patch.
     * @param input An {@link User} container that holds
     *              new values for username to update to.
     * @return The {@link User} that was updated.
     * @since 0.0.11
     * @throws sun.nio.fs.UnixUserPrincipals.User Thrown when the {@link User} cannot
     *                              be found.
     */
    User patchUser(final String username, final User input)
            throws UserNotFoundException;

}
