package com.qoajad.backend.service.user;

import com.qoajad.backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> retrieveAllUsers();
    ResponseEntity<User> readUser(Integer id);
    ResponseEntity<Void> createUser(Integer id, String password);
    ResponseEntity<Void> updateUser(Integer id, String password);
    ResponseEntity<Void> deleteUser(Integer id);
}
