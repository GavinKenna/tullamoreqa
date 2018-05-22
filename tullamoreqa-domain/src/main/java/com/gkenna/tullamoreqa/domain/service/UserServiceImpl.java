package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.service.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User User) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public void editUser(User User) {

    }

    @Override
    public boolean doesUserExist(User User) {
        return false;
    }

    @Override
    public boolean doesUserExist(long id) {
        return false;
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
