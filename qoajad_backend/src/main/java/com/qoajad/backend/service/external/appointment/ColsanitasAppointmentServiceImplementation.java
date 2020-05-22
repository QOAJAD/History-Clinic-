package com.qoajad.backend.service.external.appointment;

import com.qoajad.backend.model.external.eps.appointment.*;
import com.qoajad.backend.model.external.eps.response.Response;
import com.qoajad.backend.rpc.eps.colsanitas.ColsanitasAppointmentRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("defaultColsanitasAppointmentService")
public class ColsanitasAppointmentServiceImplementation implements AppointmentService {

    private final ColsanitasAppointmentRPC colsanitasAppointmentRPC;

    @Autowired
    public ColsanitasAppointmentServiceImplementation(@Qualifier("defaultColsanitasAppointmentRPC") final ColsanitasAppointmentRPC colsanitasAppointmentRPC) {
        this.colsanitasAppointmentRPC = colsanitasAppointmentRPC;
    }

    @Override
    public List<ConsultingRoom> findAvailableAppointments(String healthProviderInstituteName, String specialtyName) {
        return colsanitasAppointmentRPC.findAvailableAppointments(healthProviderInstituteName, specialtyName);
    }

    @Override
    public List<Appointment> findUserAppointments(int userDocument) {
        return colsanitasAppointmentRPC.findUserAppointments(userDocument);
    }

    @Override
    public CreateAppointmentResponse attemptToCreateAppointment(CreateAppointment createAppointment) {
        return colsanitasAppointmentRPC.attemptToCreateAppointment(createAppointment);
    }

    @Override
    public Response attemptToDeleteAppointment(int appointmentId) {
        return colsanitasAppointmentRPC.attemptToDeleteAppointment(appointmentId);
    }

    @Override
    public Response attemptToUpdateAppointment(UpdateAppointment updateAppointment) {
        return colsanitasAppointmentRPC.attemptToUpdateAppointment(updateAppointment);
    }
}
