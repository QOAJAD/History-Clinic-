package com.qoajad.backend.service.log.database;

import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("defaultDatabaseLogService")
public class DatabaseLogServiceImplementation implements DatabaseLogService {

    private final LogAccessor logDatabaseAccessor;

    @Autowired
    public DatabaseLogServiceImplementation(@Qualifier("defaultLogDatabaseAccessor") final LogAccessor logDatabaseAccessor) {
        this.logDatabaseAccessor = logDatabaseAccessor;
    }

    @Override
    public void logAppointmentCreation(CreateAppointmentLog createAppointmentLog) {
        logDatabaseAccessor.logAppointmentCreation(createAppointmentLog);
    }

    @Override
    public void logAppointmentUpdate(UpdateAppointmentLog updateAppointmentLog) {
        logDatabaseAccessor.logAppointmentUpdate(updateAppointmentLog);
    }
}
