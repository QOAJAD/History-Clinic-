package com.qoajad.backend.service.external.specialty;

import com.qoajad.backend.model.external.specialty.Specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> retrieveAllSpecialtys(final String healthProviderName);
}
