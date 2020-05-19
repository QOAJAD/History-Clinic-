package com.qoajad.backend.rpc.eps;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "HealthProviderInstituteService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultHealthProviderInstituteRPC")
public interface HealthProviderInstituteRPC {

    @RequestMapping(value = "/ips", method = RequestMethod.GET)
    List<HealthProviderInstitute> retrieveAllHPI();
}
