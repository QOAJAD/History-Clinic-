package com.qoajad.qoajadbackend.controller;

import com.qoajad.qoajadbackend.model.User;
import com.qoajad.qoajadbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

    @RequestMapping(value = "/retrieve-users", method = RequestMethod.GET)
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }
}
