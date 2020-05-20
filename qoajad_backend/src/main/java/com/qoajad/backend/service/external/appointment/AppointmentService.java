package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;

import java.util.List;

public interface AppointmentService {

    List<ConsultingRoom> findAvailableAppointments(final String healthProviderInstituteName, final String specialtyName);
    List<Appointment> findUserAppointments(final int userDocument);

    CreateAppointmentResponse attemptToCreateAppointment(final CreateAppointment createAppointment);
    Response attemptToDeleteAppointment(final int appointmentId);
    boolean attemptToUpdateAppointment(final UpdateAppointment updateAppointment);
}
