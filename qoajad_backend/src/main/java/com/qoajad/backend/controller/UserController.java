package com.qoajad.backend.controller;

import com.qoajad.backend.model.User;
import com.qoajad.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RefreshScope
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("defaultUserService") final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> retrieveAllUsers() {
        ResponseEntity<List<User>> response;
        try {
            response = new ResponseEntity<>(userService.retrieveAllUsers(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> readUser(@PathVariable int id) {
        ResponseEntity<User> response;
        try {
            response = new ResponseEntity<>(userService.readUser(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        ResponseEntity<String> response;
        try {
            userService.createUser(user.getId(), user.getPassword());
            response = new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        ResponseEntity<String> response;
        try {
            userService.updateUser(user.getId(), user.getPassword());
            response = new ResponseEntity<>( "1 row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        ResponseEntity<String> response;
        try {
            userService.deleteUser(id);
            response = new ResponseEntity<>( "1 row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }
}
