package com.qoajad.backend.service.internal.user;

import com.qoajad.backend.model.internal.user.CreateUser;
import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.User;

import java.util.List;

public interface UserService {

    List<User> retrieveAllUsers();
    User findUserByUsername(String username);
    void createUser(CreateUser user);
    boolean updateUser(UpdateUser user, String username);
    boolean deleteUser(String username);
}
