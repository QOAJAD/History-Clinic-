package com.qoajad.backend.rpc.hce;

import com.qoajad.backend.model.external.hce.authentication.Authentication;
import com.qoajad.backend.model.external.hce.authentication.AuthenticationResponse;
import com.qoajad.backend.model.external.hce.authentication.HealthPromotingEntityAuthentication;
import com.qoajad.backend.model.external.hce.user.User;
import feign.Headers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "HCEAuthenticationService", url = "http://34.95.198.251:3001")
@Qualifier("defaultHCEAuthenticationServiceRPC")
public interface AuthenticationRPC {

    @RequestMapping(value = "/eps/sign", method = RequestMethod.POST)
    ResponseEntity<AuthenticationResponse> attemptToAuthenticateAsHealthPromotingEntity(@RequestBody final HealthPromotingEntityAuthentication healthPromotingEntityAuthentication);

    @Headers("Content-Type: application/json")
    @RequestMapping(value = "/users/sign", method = RequestMethod.POST)
    ResponseEntity<AuthenticationResponse> attemptToAuthenticateAsUser(@RequestBody final Authentication authentication);
}
