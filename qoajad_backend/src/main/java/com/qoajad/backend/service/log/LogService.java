package com.qoajad.backend.service.log;

import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.model.log.Log;

public interface LogService {

    void logUserAuthentication(final Log log);
}
