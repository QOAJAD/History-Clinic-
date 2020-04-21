package com.qoajad.backend.service.appointment;

import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import com.qoajad.backend.rpc.AppointmentRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultAppointmentService")
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRPC appointmentRPC;

    @Autowired
    public AppointmentServiceImplementation(@Qualifier("defaultAppointmentRPC") final AppointmentRPC appointmentRPC) {
        this.appointmentRPC = appointmentRPC;
    }

    @Override
    public Appointment findAppointment(int appointmentId) {
        return appointmentRPC.findAppointment(appointmentId);
    }

    @Override
    public boolean createAppointment(CreateAppointment appointment) {
        return appointmentRPC.createAppointment(appointment);
    }

    @Override
    public boolean updateAppointment(UpdateAppointment appointment) {
        return appointmentRPC.updateAppointment(appointment);
    }
}
