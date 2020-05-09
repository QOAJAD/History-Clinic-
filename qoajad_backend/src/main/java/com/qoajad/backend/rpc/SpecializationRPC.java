package com.qoajad.backend.rpc;

import com.qoajad.backend.model.external.specialization.Specialization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "SpecializationService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultSpecializationRPC")
public interface SpecializationRPC {

    @RequestMapping(value = "ips/{healthProviderInstituteName}", method = RequestMethod.GET)
    List<Specialization> retrieveAll(@PathVariable("healthProviderInstituteName") final String healthProviderInstituteName);
}
