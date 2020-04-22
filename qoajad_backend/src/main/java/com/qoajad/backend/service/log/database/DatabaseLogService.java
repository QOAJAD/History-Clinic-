package com.qoajad.backend.service.log.database;

import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;

public interface DatabaseLogService {

    void logAppointmentCreation(final CreateAppointmentLog createAppointmentLog);
    void logAppointmentUpdate(final UpdateAppointmentLog updateAppointmentLog);
}
