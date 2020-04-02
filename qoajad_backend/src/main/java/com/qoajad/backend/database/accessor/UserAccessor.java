package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.User;

import java.util.List;

public interface UserAccessor {

    List<User> retrieveAllUsers();
    User readUser(int id);
    Void createUser(int  id, String password);
    int updateUser(int id, String password);
    int deleteUser(int id);
}
