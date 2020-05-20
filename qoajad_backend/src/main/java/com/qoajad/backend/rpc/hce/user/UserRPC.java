package com.qoajad.backend.rpc.hce.user;

import com.qoajad.backend.model.external.hce.authentication.Authentication;
import com.qoajad.backend.model.external.hce.authentication.AuthenticationResponse;
import com.qoajad.backend.model.external.hce.user.User;
import com.qoajad.backend.model.external.hce.user.UserResponse;
import feign.Headers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "HCEUserService", url = "http://34.95.198.251:3001")
@Qualifier("defaultHCEUserServiceRPC")
public interface UserRPC {

    @Headers("Content-Type: application/json")
    @RequestMapping(value = "/users/getPaciente", method = RequestMethod.POST)
    ResponseEntity<String> attemptToRetrieveUserInformation(@RequestHeader("Authorization") String authorization,
                                                            @RequestBody final User user);

    @Headers("Content-Type: application/json")
    @RequestMapping(value = "/users/getHC", method = RequestMethod.POST)
    ResponseEntity<String> attemptToRetrieveMH(@RequestHeader("Authorization") String authorization,
                                                               @RequestBody final User user);
}
