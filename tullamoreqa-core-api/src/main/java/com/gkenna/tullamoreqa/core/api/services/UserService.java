/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

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
    void addUser(final User user);

    void deleteUser(final User user);

    User deleteUser(final String id);

    User updateUser(final String username, final User input);

    boolean doesUserExist(final User user);

    boolean doesUserExist(final String username);

    User getUser(final String id);

    User[] getAllUsers();

}
