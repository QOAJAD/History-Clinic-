package com.qoajad.backend.service.external.specialization;

import com.qoajad.backend.model.external.specialization.Specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> retrieveAllSpecializations(final String healthProviderName);
}
