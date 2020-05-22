package com.qoajad.backend.service.external.specialty;

import com.qoajad.backend.model.external.eps.specialty.Specialties;
import com.qoajad.backend.rpc.eps.colsanitas.ColsanitasSpecialtyRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("defaultColsanitasSpecialtyService")
public class ColsanitasSpecialtyServiceImplementation implements SpecialtyService {

    private final ColsanitasSpecialtyRPC colsanitasSpecialtyRPC;

    @Autowired
    public ColsanitasSpecialtyServiceImplementation(@Qualifier("defaultColsanitasSpecialtyRPC") final ColsanitasSpecialtyRPC colsanitasSpecialtyRPC) {
        this.colsanitasSpecialtyRPC = colsanitasSpecialtyRPC;
    }

    @Override
    public List<Specialties> retrieveAllSpecialties(String healthProviderName) {
        return Arrays.asList(colsanitasSpecialtyRPC.retrieveAllSpecialties(healthProviderName));
    }
}
