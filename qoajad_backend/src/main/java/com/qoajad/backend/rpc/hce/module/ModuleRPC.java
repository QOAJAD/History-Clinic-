package com.qoajad.backend.rpc.hce.module;

import com.qoajad.backend.model.external.hce.user.UpdateUser;
import com.qoajad.backend.model.external.hce.user.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "HCEModuleService", url = "http://34.95.198.251:3001", configuration = {HCEModuleFeignInterceptor.class})
@Qualifier("defaultHCEModuleServiceRPC")
public interface ModuleRPC {

    @RequestMapping(value = "/users/modifyPassword", method = RequestMethod.POST)
    ResponseEntity<UpdateUserResponse> attemptToUpdateUser(@RequestBody final UpdateUser updateUser);
}