package com.qoajad.backend.model.internal.log;

import com.qoajad.backend.model.external.appointment.Appointment;

public class UpdateAppointmentLog {
    private final Appointment oldAppointment;
    private final Appointment newAppointment;

    public UpdateAppointmentLog(Appointment oldAppointment, Appointment newAppointment) {
        this.oldAppointment = oldAppointment;
        this.newAppointment = newAppointment;
    }

    public Appointment getOldAppointment() {
        return oldAppointment;
    }

    public Appointment getNewAppointment() {
        return newAppointment;
    }
}
