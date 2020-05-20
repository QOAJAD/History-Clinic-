package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.eps.appointment.Appointment;
import com.qoajad.backend.model.external.eps.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.eps.appointment.CreateAppointment;
import com.qoajad.backend.model.external.eps.appointment.UpdateAppointment;

import java.util.List;

public interface AppointmentService {

    List<ConsultingRoom> findAvailableAppointments(final String healthProviderInstituteName, final String specialtyName);
    List<Appointment> findUserAppointments(final int userDocument);

    boolean attemptToCreateAppointment(final CreateAppointment createAppointment);
    boolean attemptToDeleteAppointment(final int appointmentId);
    boolean attemptToUpdateAppointment(final UpdateAppointment updateAppointment);

    Appointment findUserAppointment(final int userDocument, final int appointmentId);
}
