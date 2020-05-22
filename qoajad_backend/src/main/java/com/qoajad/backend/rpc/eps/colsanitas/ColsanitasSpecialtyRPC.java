package com.qoajad.backend.rpc.eps.colsanitas;

import com.qoajad.backend.model.external.eps.specialty.Specialties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ColsanitasSpecialtyService", url = "http://thawing-stream-48846.herokuapp.com")
@Qualifier("defaultColsanitasSpecialtyRPC")
public interface ColsanitasSpecialtyRPC {

    @RequestMapping(value = "/ips/{healthProviderInstituteName}", method = RequestMethod.GET)
    Specialties[] retrieveAllSpecialties(@PathVariable("healthProviderInstituteName") final String healthProviderInstituteName);
}
