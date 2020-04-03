package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserAccessor {

    List<User> retrieveAllUsers();
    User readUser(int id);
    void createUser(int id, String password);
    boolean updateUser(int id, String password);
    boolean deleteUser(int id);
}
