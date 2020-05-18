package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.eps.response.Response;
import com.qoajad.backend.model.external.hce.user.UpdateUserResponse;
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

    @RequestMapping(value = "/user/find/{document}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserByDocument(@PathVariable("document") int document) {
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

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        ResponseEntity<String> response;
        try {
            final ResponseEntity<UpdateUserResponse> hceResponse = userRPC.attemptToUpdateUser(new com.qoajad.backend.model.external.hce.user.UpdateUser(user.getDocument(), user.getPassword()));
            // The user password was able to be updated in hce.
            if (hceResponse.getStatusCode() == HttpStatus.OK) {
                userService.createUser(user);
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
            final ResponseEntity<UpdateUserResponse> hceResponse = userRPC.attemptToUpdateUser(new com.qoajad.backend.model.external.hce.user.UpdateUser(user.getDocument(), user.getPassword()));
            int rowsUpdated = 0;
            // TODO(Juan): Make the email change in a new controller different from this one, the reason to do that is that we can update our email
            // and in the current flow of code, if our new email is already taken the database will throw an exception but at that point we have already
            // updated the user password in hce service and we don't have any rollbacks.

            // The user password was able to be updated in hce.
            if (hceResponse.getStatusCode() == HttpStatus.OK) {
                rowsUpdated = userService.updateUser(user) ? 1 : 0;
            }
            response = new ResponseEntity<>(rowsUpdated + " row(s) changed.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return response;
    }

    @RequestMapping(value = "/user/delete/{document}", method = RequestMethod.DELETE)
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
