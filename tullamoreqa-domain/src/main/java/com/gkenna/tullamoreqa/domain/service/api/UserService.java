package com.gkenna.tullamoreqa.domain.service.api;

import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user);
    void deleteUser(User user);
    void deleteUser(long id);
    void editUser(User user);
    boolean doesUserExist(User user);
    boolean doesUserExist(long id);
    User getUser(String id);
    User[] getAllUsers();

}
