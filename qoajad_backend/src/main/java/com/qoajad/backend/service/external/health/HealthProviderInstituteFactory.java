package com.qoajad.backend.service.external.health;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultHealthProviderInstituteFactory")
public class HealthProviderInstituteFactory {

    private HealthProviderInstituteService suraHealthProviderInstituteService;
    private HealthProviderInstituteService colsanitasHealthProviderInstituteService;

    @Autowired
    public HealthProviderInstituteFactory(@Qualifier("defaultSuraHealthProviderInstituteService") final HealthProviderInstituteService suraHealthProviderInstituteService,
                                          @Qualifier("defaultColsanitasHealthProviderInstituteService") final HealthProviderInstituteService colsanitasHealthProviderInstituteService) {
        this.suraHealthProviderInstituteService = suraHealthProviderInstituteService;
        this.colsanitasHealthProviderInstituteService = colsanitasHealthProviderInstituteService;
    }

    public HealthProviderInstituteService create(final String healthPromotingEntityName) {
        switch (healthPromotingEntityName) {
            case "Colsanitas":
                return suraHealthProviderInstituteService;
            case "SURA":
                return colsanitasHealthProviderInstituteService;
            default:
                throw new IllegalStateException("The health promoting entity name is not a valid one.");
        }
    }
}