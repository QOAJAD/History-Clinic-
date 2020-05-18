package com.qoajad.backend.rpc;

import com.qoajad.backend.model.external.specialty.Specialties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "SpecialtyService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultSpecialtyRPC")
public interface SpecialtyRPC {

    @RequestMapping(value = "/ips/{healthProviderInstituteName}", method = RequestMethod.GET)
    List<Specialties> retrieveAllSpecialties(@PathVariable("healthProviderInstituteName") final String healthProviderInstituteName);
}
