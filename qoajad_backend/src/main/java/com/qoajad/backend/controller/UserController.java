package com.qoajad.backend.controller;

import com.qoajad.backend.model.UpdateUser;
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

    @RequestMapping(value = "/user/{document}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserByDocument(@PathVariable int document) {
        ResponseEntity<User> response;
        try {
            response = new ResponseEntity<>(userService.findUserByDocument(document), HttpStatus.OK);
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
            userService.createUser(user.getDocument(), user.getPassword());
            response = new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UpdateUser user) {
        ResponseEntity<String> response;
        try {
            int rowsUpdated = userService.updateUser(user.getOldDocument(), user.getNewDocument(), user.getPassword()) ? 1 : 0;
            response = new ResponseEntity<>(rowsUpdated + " row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/delete/user/{document}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable int document) {
        ResponseEntity<String> response;
        try {
            int rowsDeleted = userService.deleteUser(document) ? 1 : 0;
            response = new ResponseEntity<>(rowsDeleted + " row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }
}
