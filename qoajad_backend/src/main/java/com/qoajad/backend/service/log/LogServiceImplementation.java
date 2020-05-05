package com.qoajad.backend.service.log;

import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.model.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultLogService")
public class LogServiceImplementation implements LogService {

    private final LogAccessor logDatabaseAccessor;

    @Autowired
    public LogServiceImplementation(@Qualifier("defaultLogDatabaseAccessor") final LogAccessor logDatabaseAccessor) {
        this.logDatabaseAccessor = logDatabaseAccessor;
    }

    @Override
    public void log(Log log) {
        logDatabaseAccessor.log(log);
    }
}
