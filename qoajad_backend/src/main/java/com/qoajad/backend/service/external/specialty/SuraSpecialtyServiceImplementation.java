package com.qoajad.backend.service.external.specialty;

import com.qoajad.backend.model.external.eps.specialty.Specialties;
import com.qoajad.backend.rpc.eps.sura.SuraSpecialtyRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("defaultSuraSpecialtyService")
public class SuraSpecialtyServiceImplementation implements SpecialtyService {

    private final SuraSpecialtyRPC suraSpecialtyRPC;

    @Autowired
    public SuraSpecialtyServiceImplementation(@Qualifier("defaultSuraSpecialtyRPC") final SuraSpecialtyRPC suraSpecialtyRPC) {
        this.suraSpecialtyRPC = suraSpecialtyRPC;
    }

    @Override
    public List<Specialties> retrieveAllSpecialties(String healthProviderName) {
        return Arrays.asList(suraSpecialtyRPC.retrieveAllSpecialties(healthProviderName));
    }
}
