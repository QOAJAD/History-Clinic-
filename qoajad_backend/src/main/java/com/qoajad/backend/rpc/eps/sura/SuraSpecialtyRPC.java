package com.qoajad.backend.rpc.eps.sura;

import com.qoajad.backend.model.external.eps.specialty.Specialties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SuraSpecialtyService", url = "http://127.0.0.1")
@Qualifier("defaultSuraSpecialtyRPC")
public interface SuraSpecialtyRPC {

    @RequestMapping(value = "/dummy_hpi/find_specialties/{healthProviderInstituteName}", method = RequestMethod.GET)
    Specialties[] retrieveAllSpecialties(@PathVariable("healthProviderInstituteName") final String healthProviderInstituteName);
}
