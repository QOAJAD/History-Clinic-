package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserAccessor {

    List<User> retrieveAllUsers();
    User findUserByDocument(int document);
    void createUser(int document, String password);
    boolean updateUser(int document, String password);
    boolean deleteUser(int document);
}
