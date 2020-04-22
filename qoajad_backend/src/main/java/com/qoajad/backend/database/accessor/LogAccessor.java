package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;

public interface LogAccessor {

    void logAppointmentCreation(final CreateAppointmentLog createAppointmentLog);
    void logAppointmentUpdate(final UpdateAppointmentLog updateAppointmentLog);
}
