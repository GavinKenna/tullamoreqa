/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        LOGGER.debug("Adding New User {}", user);
        userRepository.save(user);
        LOGGER.debug("New User with ID {} added successfully.", user.getUsername());
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User deleteUser(String id) {
        return null;
    }

    @Override
    public User updateUser(String username, User input) {
        return null;
    }

    @Override
    public boolean doesUserExist(User user) {
        return false;
    }

    @Override
    public boolean doesUserExist(String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User[] getAllUsers() {
        return new User[0];
    }
}
