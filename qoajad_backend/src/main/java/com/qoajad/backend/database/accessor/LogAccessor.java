package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.model.log.Log;

public interface LogAccessor {

    void logUserAuthentication(final Log log);
}
