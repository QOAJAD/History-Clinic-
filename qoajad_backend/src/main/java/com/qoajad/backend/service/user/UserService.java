package com.qoajad.backend.service.user;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> retrieveAllUsers();
    User findUserByDocument(int document);
    void createUser(int document, String password);
    boolean updateUser(int document, String password);
    boolean deleteUser(int document);
}
