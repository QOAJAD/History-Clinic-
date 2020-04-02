package com.qoajad.backend.controller;

import com.qoajad.backend.model.User;
import com.qoajad.backend.service.user.UserService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class DefaultController {

    private final UserService userService;

    @Autowired
    public DefaultController(@Qualifier("defaultUserService") final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> readUser(@PathVariable Integer id) {
        return userService.readUser(id);
    }

    @RequestMapping(value = "/create/user/{id}/{password}", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@PathVariable Integer id, @PathVariable String password) {
        return userService.createUser(id, password);
    }

    @RequestMapping(value = "/update/user/{id}/{password}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @PathVariable String password) {
        return userService.updateUser(id, password);
    }

    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
