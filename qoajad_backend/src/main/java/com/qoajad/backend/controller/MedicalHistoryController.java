package com.qoajad.backend.controller;

import com.qoajad.backend.model.external.hce.authentication.Authentication;
import com.qoajad.backend.model.external.hce.authentication.AuthenticationResponse;
import com.qoajad.backend.model.external.hce.user.UserResponse;
import com.qoajad.backend.model.internal.user.User;
import com.qoajad.backend.rpc.hce.AuthenticationRPC;
import com.qoajad.backend.rpc.hce.module.ModuleRPC;
import com.qoajad.backend.rpc.hce.user.UserRPC;
import com.qoajad.backend.service.internal.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MedicalHistoryController {

    private final UserService userService;
    private final AuthenticationRPC authenticationRPC;
    private final UserRPC userRPC;

    @Autowired
    public MedicalHistoryController(@Qualifier("defaultUserService") final UserService userService,
                                    @Qualifier("defaultHCEAuthenticationServiceRPC") AuthenticationRPC authenticationRPC,
                                    @Qualifier("defaultHCEUserServiceRPC") UserRPC userRPC) {
        this.userService = userService;
        this.authenticationRPC = authenticationRPC;
        this.userRPC = userRPC;
    }

    @RequestMapping(value = "/medical_history/retrieve_information/{username}/{password}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> retrieveMedicalHistory(@PathVariable("username") String username, @PathVariable("password") String password) {
        ResponseEntity<UserResponse> response;
        Authentication authentication = new Authentication(userService.findUserByUsername(username).getDocument(), password);
        final ResponseEntity<AuthenticationResponse> authenticationResponse = authenticationRPC.attemptToAuthenticateAsUser(authentication);
        if (authenticationResponse.getStatusCode() == HttpStatus.OK) {
            final String jwt = authenticationResponse.getBody().getToken();
            com.qoajad.backend.model.external.hce.user.User user = new com.qoajad.backend.model.external.hce.user.User(authentication.getId());
            ResponseEntity<String> userRPCResponse = userRPC.attemptToRetrieveMH(jwt, user);
            String userInformation = userRPCResponse.getBody();

            response = new ResponseEntity<>(new UserResponse("Accepted", userInformation), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(new UserResponse("Declined", null), HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
