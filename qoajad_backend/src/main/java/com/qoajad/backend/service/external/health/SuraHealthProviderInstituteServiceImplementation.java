package com.qoajad.backend.service.external.health;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import com.qoajad.backend.rpc.eps.sura.SuraHealthProviderInstituteRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultSuraHealthProviderInstituteService")
public class SuraHealthProviderInstituteServiceImplementation implements HealthProviderInstituteService {

    private final SuraHealthProviderInstituteRPC suraHealthProviderInstituteRPC;

    @Autowired
    public SuraHealthProviderInstituteServiceImplementation(@Qualifier("defaultSuraHealthProviderInstituteRPC") final SuraHealthProviderInstituteRPC suraHealthProviderInstituteRPC) {
        this.suraHealthProviderInstituteRPC = suraHealthProviderInstituteRPC;
    }

    @Override
    public List<HealthProviderInstitute> retrieveAllHPI() {
        return suraHealthProviderInstituteRPC.retrieveAllHPI();
    }
}
