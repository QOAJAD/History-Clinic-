package com.qoajad.backend.service.external.specialty;

import com.qoajad.backend.model.external.specialty.Specialties;
import com.qoajad.backend.rpc.SpecialtyRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultSpecialtyService")
public class SpecialtyServiceImplementation implements SpecialtyService {

    private final SpecialtyRPC specialtyRPC;

    @Autowired
    public SpecialtyServiceImplementation(@Qualifier("defaultSpecialtyRPC") final SpecialtyRPC specialtyRPC) {
        this.specialtyRPC = specialtyRPC;
    }

    @Override
    public List<Specialties> retrieveAllSpecialties(String healthProviderName) {
        return specialtyRPC.retrieveAllSpecialties(healthProviderName);
    }
}
