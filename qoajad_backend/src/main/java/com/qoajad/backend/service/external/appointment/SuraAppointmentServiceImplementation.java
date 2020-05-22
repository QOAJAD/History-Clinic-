package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;
import com.qoajad.backend.rpc.eps.sura.SuraAppointmentRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultSuraAppointmentService")
public class SuraAppointmentServiceImplementation implements AppointmentService {

    private final SuraAppointmentRPC suraAppointmentRPC;

    @Autowired
    public SuraAppointmentServiceImplementation(@Qualifier("defaultSuraAppointmentRPC") final SuraAppointmentRPC suraAppointmentRPC) {
        this.suraAppointmentRPC = suraAppointmentRPC;
    }

    @Override
    public List<ConsultingRoom> findAvailableAppointments(String healthProviderInstituteName, String specialtyName) {
        return suraAppointmentRPC.findAvailableAppointments(healthProviderInstituteName, specialtyName);
    }

    @Override
    public List<Appointment> findUserAppointments(int userDocument) {
        return suraAppointmentRPC.findUserAppointments(userDocument);
    }

    @Override
    public CreateAppointmentResponse attemptToCreateAppointment(CreateAppointment createAppointment) {
        return suraAppointmentRPC.attemptToCreateAppointment(createAppointment);
    }

    @Override
    public Response attemptToDeleteAppointment(int appointmentId) {
        return suraAppointmentRPC.attemptToDeleteAppointment(appointmentId);
    }

    @Override
    public Response attemptToUpdateAppointment(UpdateAppointment updateAppointment) {
        return suraAppointmentRPC.attemptToUpdateAppointment(updateAppointment);
    }
}
