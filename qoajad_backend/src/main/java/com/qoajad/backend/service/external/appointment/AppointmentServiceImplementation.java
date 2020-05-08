package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.appointment.Appointment;
import com.qoajad.backend.model.external.appointment.AvailableAppointment;
import com.qoajad.backend.model.external.appointment.ConsultingRoom;
import com.qoajad.backend.model.external.appointment.CreateAppointment;
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
    public List<ConsultingRoom> findAvailableAppointments(String healthProviderInstituteName, String specializationName) {
        return appointmentRPC.findAvailableAppointments(healthProviderInstituteName, specializationName);
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
}
