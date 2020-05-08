package com.qoajad.backend.service.external.health;

import com.qoajad.backend.model.external.health.HealthProviderInstitute;
import com.qoajad.backend.rpc.HealthProviderInstituteRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultHealthProviderInstituteService")
public class HealthProviderInstituteServiceImplementation implements HealthProviderInstituteService {

    private final HealthProviderInstituteRPC healthProviderInstituteRPC;

    @Autowired
    public HealthProviderInstituteServiceImplementation(@Qualifier("defaultHealthProviderInstituteRPC") final HealthProviderInstituteRPC healthProviderInstituteRPC) {
        this.healthProviderInstituteRPC = healthProviderInstituteRPC;
    }

    @Override
    public List<HealthProviderInstitute> retrieveAll() {
        return healthProviderInstituteRPC.retrieveAll();
    }
}
