package com.qoajad.backend.service.user;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> retrieveAllUsers();
    User readUser(int id);
    Void createUser(int  id, String password);
    int updateUser(int id, String password);
    int deleteUser(int id);
}
