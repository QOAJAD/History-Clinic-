package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;
import com.qoajad.backend.rpc.eps.AppointmentRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public CreateAppointmentResponse attemptToCreateAppointment(CreateAppointment createAppointment) {
        return appointmentRPC.attemptToCreateAppointment(createAppointment);
    }

    @Override
    public boolean attemptToDeleteAppointment(int appointmentId) {
        //TODO: Response has not been handled. There should be a serialization of it so it can return either true or false
        Response response = appointmentRPC.attemptToDeleteAppointment(appointmentId);
        return true;
    }

    @Override
    public boolean attemptToUpdateAppointment(UpdateAppointment updateAppointment) {
        //TODO: Response has not been handled. There should be a serialization of it so it can return either true or false
        Response response = appointmentRPC.attemptToUpdateAppointment(updateAppointment);
        return true;
    }
}
