package com.qoajad.backend.service.external.health;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;
import com.qoajad.backend.rpc.eps.colsanitas.ColsanitasHealthProviderInstituteRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultColsanitasHealthProviderInstituteService")
public class ColsanitasHealthProviderInstituteServiceImplementation implements HealthProviderInstituteService {

    private final ColsanitasHealthProviderInstituteRPC colsanitasHealthProviderInstituteRPC;

    @Autowired
    public ColsanitasHealthProviderInstituteServiceImplementation(@Qualifier("defaultColsanitasHealthProviderInstituteRPC") final ColsanitasHealthProviderInstituteRPC colsanitasHealthProviderInstituteRPC) {
        this.colsanitasHealthProviderInstituteRPC = colsanitasHealthProviderInstituteRPC;
    }

    @Override
    public List<HealthProviderInstitute> retrieveAllHPI() {
        return colsanitasHealthProviderInstituteRPC.retrieveAllHPI();
    }
}
