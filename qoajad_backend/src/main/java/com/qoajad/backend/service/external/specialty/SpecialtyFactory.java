package com.qoajad.backend.service.external.specialty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultSpecialtyFactory")
public class SpecialtyFactory {

    private SpecialtyService suraSpecialtyService;
    private SpecialtyService colsanitasSpecialtyService;

    @Autowired
    public SpecialtyFactory(@Qualifier("defaultSuraSpecialtyService") final SpecialtyService suraSpecialtyService,
                            @Qualifier("defaultColsanitasSpecialtyService") final SpecialtyService colsanitasSpecialtyService) {
        this.suraSpecialtyService = suraSpecialtyService;
        this.colsanitasSpecialtyService = colsanitasSpecialtyService;
    }

    public SpecialtyService create(final String healthPromotingEntityName) {
        switch (healthPromotingEntityName) {
            case "Colsanitas":
                return colsanitasSpecialtyService;
            case "SURA":
                return suraSpecialtyService;
            default:
                throw new IllegalStateException("The health promoting entity name is not a valid one.");
        }
    }
}