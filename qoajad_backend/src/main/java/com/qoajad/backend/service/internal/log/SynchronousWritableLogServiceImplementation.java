package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.internal.log.LogCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("synchronousWritableLogService")
public class SynchronousWritableLogServiceImplementation implements WritableLogService {

    private final LogAccessor logDatabaseAccessor;

    @Autowired
    public SynchronousWritableLogServiceImplementation(@Qualifier("defaultLogDatabaseAccessor") final LogAccessor logDatabaseAccessor) {
        this.logDatabaseAccessor = logDatabaseAccessor;
    }

    @Override
    public void log(LogCreate logCreate) {
        logDatabaseAccessor.log(logCreate);
    }
}
