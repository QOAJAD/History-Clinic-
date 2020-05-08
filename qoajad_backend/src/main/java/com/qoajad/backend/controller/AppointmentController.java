package com.qoajad.backend.controller;

import com.qoajad.backend.service.external.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(@Qualifier("defaultAppointmentService") final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

   // TODO(Juan, AntonioYu): This has to be redone based on the api requirements. The services are done, so we just need to fulfill the service with the parameters.
}
