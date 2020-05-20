package com.qoajad.backend.controller;

import com.qoajad.backend.model.internal.authentication.AuthenticationResponse;
import com.qoajad.backend.service.internal.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(final AuthenticationManager authenticationManager, @Qualifier("defaultAuthenticationService") final AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/authentication/authenticate/{username}/{password}", method = RequestMethod.GET)
    public ResponseEntity<AuthenticationResponse> authenticate(@PathVariable("username") final String username, @PathVariable("password") final String password) {
        final boolean couldAuthenticate = attemptToAuthenticate(username, password);
        AuthenticationResponse authenticationResponse;
        ResponseEntity<AuthenticationResponse> response;
        if (!couldAuthenticate) {
            authenticationResponse = new AuthenticationResponse("Invalid username or password.", "");
            response = new ResponseEntity<>(authenticationResponse, HttpStatus.UNAUTHORIZED);
        } else {
            authenticationResponse = new AuthenticationResponse("Valid login.", authenticationService.generateJWT(username));
            response = new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        }
        return response;
    }

    private boolean attemptToAuthenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
