package com.qoajad.backend.service.appointment;

import com.qoajad.backend.database.accessor.AppointmentAccessor;
import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultAppointmentService")
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentAccessor databaseAccessor;

    @Autowired
    public AppointmentServiceImplementation(@Qualifier("defaultAppointmentDatabaseAccessor") final AppointmentAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    @Override
    public Appointment findAppointment(int appointmentId) {
        return databaseAccessor.findAppointment(appointmentId);
    }

    @Override
    public boolean createAppointment(CreateAppointment appointment) {
        return databaseAccessor.createAppointment(appointment);
    }

    @Override
    public boolean updateAppointment(UpdateAppointment appointment) {
        return databaseAccessor.updateAppointment(appointment);
    }
}
