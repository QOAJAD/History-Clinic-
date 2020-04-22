package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.user.UpdateUser;
import com.qoajad.backend.model.user.User;

import java.util.List;

public interface UserAccessor {

    List<User> retrieveAllUsers();
    User findUserByDocument(int document);
    void createUser(User user);
    boolean updateUser(UpdateUser user);
    boolean deleteUser(int document);
}
