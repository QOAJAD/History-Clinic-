package com.qoajad.backend.service.external.appointment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultAppointmentFactory")
public class AppointmentFactory {

    private AppointmentService suraAppointmentService;
    private AppointmentService colsanitasAppointmentService;

    @Autowired
    public AppointmentFactory(@Qualifier("defaultSuraAppointmentService") final AppointmentService suraAppointmentService,
                              @Qualifier("defaultColsanitasAppointmentService") final AppointmentService colsanitasAppointmentService) {
        this.suraAppointmentService = suraAppointmentService;
        this.colsanitasAppointmentService = colsanitasAppointmentService;
    }

    public AppointmentService create(final String healthPromotingEntityName) {
        switch (healthPromotingEntityName) {
            case "Colsanitas":
                return colsanitasAppointmentService;

            case "SURA":
                return suraAppointmentService;
            default:
                throw new IllegalStateException("The health promoting entity name is not a valid one.");
        }
    }
}