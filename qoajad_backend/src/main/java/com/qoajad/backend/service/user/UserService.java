package com.qoajad.backend.service.user;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> retrieveAllUsers();
    User findUserById(int id);
    void createUser(int id, String password);
    boolean updateUser(int id, String password);
    boolean deleteUser(int id);
}
