package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.appointment.Appointment;
import com.qoajad.backend.model.external.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.appointment.CreateAppointment;
import com.qoajad.backend.model.external.appointment.UpdateAppointment;
import com.qoajad.backend.model.external.response.Response;

import java.util.List;

public interface AppointmentService {

    List<ConsultingRoom> findAvailableAppointments(final String healthProviderInstituteName, final String specialtyName);
    List<Appointment> findUserAppointments(final int document);

    Response attemptToCreateAppointment(final CreateAppointment createAppointment);
    Response attemptToDeleteAppointment(final int appointmentId);
    Response attemptToUpdateAppointment(final UpdateAppointment updateAppointment);
}
