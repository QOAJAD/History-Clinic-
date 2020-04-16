package com.qoajad.backend.service.appointment;

import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;

public interface AppointmentService {

    Appointment findAppointment(final int appointmentId);
    boolean createAppointment(final CreateAppointment appointment);
    boolean updateAppointment(final UpdateAppointment appointment);
}
