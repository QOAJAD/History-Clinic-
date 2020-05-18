package com.qoajad.backend.service.external.health;

import com.qoajad.backend.model.external.eps.health.HealthProviderInstitute;

import java.util.List;

public interface HealthProviderInstituteService {

    List<HealthProviderInstitute> retrieveAllHPI();
}
