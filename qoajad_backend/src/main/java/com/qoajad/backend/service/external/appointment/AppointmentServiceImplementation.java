package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.appointment.*;
import com.qoajad.backend.model.external.response.Response;
import com.qoajad.backend.rpc.AppointmentRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultAppointmentService")
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRPC appointmentRPC;

    @Autowired
    public AppointmentServiceImplementation(@Qualifier("defaultAppointmentRPC") final AppointmentRPC appointmentRPC) {
        this.appointmentRPC = appointmentRPC;
    }

    @Override
    public List<ConsultingRoom> findAvailableAppointments(String healthProviderInstituteName, String specialtyName) {
        return appointmentRPC.findAvailableAppointments(healthProviderInstituteName, specialtyName);
    }

    @Override
    public List<Appointment> findUserAppointments(int userDocument) {
        return appointmentRPC.findUserAppointments(userDocument);
    }

    @Override
    public Response attemptToCreateAppointment(CreateAppointment createAppointment) {
        return appointmentRPC.attemptToCreateAppointment(createAppointment);
    }

    @Override
    public Response attemptToDeleteAppointment(int appointmentId) {
        return appointmentRPC.attemptToDeleteAppointment(appointmentId);
    }

    @Override
    public Response attemptToUpdateAppointment(UpdateAppointment updateAppointment) {
        return appointmentRPC.attemptToUpdateAppointment(updateAppointment);
    }
}
