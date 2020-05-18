package com.qoajad.backend.rpc.hce;

import com.qoajad.backend.model.external.hce.user.UpdateUser;
import com.qoajad.backend.model.external.eps.response.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "HCEUserService", url = "http://thawing-stream-48846.herokuapp.com", configuration = {HCEFeignInterceptor.class})
@Qualifier("defaultHCEUserServiceRPC")
public interface UserRPC {

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    ResponseEntity<Response> attemptToUpdateUser(@RequestBody final UpdateUser updateUser);
}