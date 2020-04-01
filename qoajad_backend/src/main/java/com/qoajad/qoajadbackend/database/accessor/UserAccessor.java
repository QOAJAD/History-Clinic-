package com.qoajad.qoajadbackend.database.accessor;

import com.qoajad.qoajadbackend.model.User;

import java.util.List;

public interface UserAccessor {

    List<User> retrieveAllUsers();
}
