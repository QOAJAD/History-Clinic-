package com.qoajad.backend.service.external.specialty;

import com.qoajad.backend.model.external.specialty.Specialties;

import java.util.List;

public interface SpecialtyService {

    List<Specialties> retrieveAllSpecialties(final String healthProviderName);
}
