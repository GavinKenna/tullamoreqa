package com.gkenna.tullamoreqa.domain.service.api;

import com.gkenna.tullamoreqa.domain.User;

public interface UserService {
    void addUser(User User);
    void deleteUser(User User);
    void deleteUser(long id);
    void editUser(User User);
    boolean doesUserExist(User User);
    boolean doesUserExist(long id);
    User getUser(String id);
    User[] getAllUsers();

}
