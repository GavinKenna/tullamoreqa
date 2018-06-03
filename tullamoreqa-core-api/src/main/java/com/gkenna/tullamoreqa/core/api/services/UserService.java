/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    User deleteUser(String id);

    User updateUser(String username, User input);

    boolean doesUserExist(User user);

    boolean doesUserExist(String username);

    User getUser(String id);

    User[] getAllUsers();


}
