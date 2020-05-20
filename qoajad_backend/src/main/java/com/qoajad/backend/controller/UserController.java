package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.hce.user.UpdateUserResponse;
import com.qoajad.backend.model.internal.user.CreateUser;
import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.User;
import com.qoajad.backend.rpc.hce.UserRPC;
import com.qoajad.backend.service.internal.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RefreshScope
@RestController
public class UserController {

    private final UserRPC userRPC;
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("defaultUserService") final UserService userService, @Qualifier("defaultHCEUserServiceRPC") final UserRPC userRPC) {
        this.userService = userService;
        this.userRPC = userRPC;
    }

    @RequestMapping(value = "/user/list_all", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user/find", method = RequestMethod.GET)
    public ResponseEntity<User> findUserByUsername() {
        ResponseEntity<User> response;
        try {
            UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            response = new ResponseEntity<>(userService.findUserByUsername(currentUser.getUsername()), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody CreateUser user) {
        ResponseEntity<String> response;
        try {
            final ResponseEntity<UpdateUserResponse> hceResponse = userRPC.attemptToUpdateUser(new com.qoajad.backend.model.external.hce.user.UpdateUser(user.getDocument(), user.getPassword()));
            // The user password was able to be updated in hce.
            if (hceResponse.getStatusCode() == HttpStatus.OK) {
                userService.createUser(user);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            response = new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UpdateUser user) {
        ResponseEntity<String> response;
        try {
            UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // The user password was able to be updated in hce.
            int rowsUpdated = userService.updateUser(user, currentUser.getUsername()) ? 1 : 0;
            if (rowsUpdated > 0) {
                // This should never fail due that we assume their backend is always active.
                userRPC.attemptToUpdateUser(new com.qoajad.backend.model.external.hce.user.UpdateUser(user.getDocument(), user.getPassword()));
            }
            response = new ResponseEntity<>(rowsUpdated + " row(s) changed.", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser() {
        ResponseEntity<String> response;
        try {
            UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int rowsDeleted = userService.deleteUser(currentUser.getUsername()) ? 1 : 0;
            response = new ResponseEntity<>(rowsDeleted + " row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }
}
