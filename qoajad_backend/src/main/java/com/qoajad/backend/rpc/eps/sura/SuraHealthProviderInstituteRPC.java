package com.qoajad.backend.rpc.eps.sura;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "SuraHealthProviderInstituteService", url = "http://127.0.0.1")
@Qualifier("defaultSuraHealthProviderInstituteRPC")
public interface SuraHealthProviderInstituteRPC {

    @RequestMapping(value = "/dummy_hpi/list_all", method = RequestMethod.GET)
    List<HealthProviderInstitute> retrieveAllHPI();
}
