package com.qoajad.backend.rpc.eps.colsanitas;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "ColsanitasHealthProviderInstituteService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultColsanitasHealthProviderInstituteRPC")
public interface ColsanitasHealthProviderInstituteRPC {

    @RequestMapping(value = "/ips", method = RequestMethod.GET)
    List<HealthProviderInstitute> retrieveAllHPI();
}
