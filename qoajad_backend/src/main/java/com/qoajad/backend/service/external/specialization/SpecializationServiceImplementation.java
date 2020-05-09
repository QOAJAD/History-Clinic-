package com.qoajad.backend.service.external.specialization;

import com.qoajad.backend.model.external.specialization.Specialization;
import com.qoajad.backend.rpc.SpecializationRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultSpecializationService")
public class SpecializationServiceImplementation implements SpecializationService {

    private final SpecializationRPC specializationRPC;

    @Autowired
    public SpecializationServiceImplementation(@Qualifier("defaultSpecializationRPC") final SpecializationRPC specializationRPC) {
        this.specializationRPC = specializationRPC;
    }

    @Override
    public List<Specialization> retrieveAllSpecializations(String healthProviderName) {
        return specializationRPC.retrieveAll(healthProviderName);
    }
}
