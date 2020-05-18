package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.internal.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("synchronousReadableLogService")
public class SynchronousReadableLogServiceImplementation implements ReadableLogService {

    private final LogAccessor logAccessor;

    @Autowired
    public SynchronousReadableLogServiceImplementation(@Qualifier("defaultLogDatabaseAccessor") final LogAccessor logAccessor) {
        this.logAccessor = logAccessor;
    }

    @Override
    public List<Log> retrieveAllLogs() {
        return logAccessor.retrieveAllLogs();
    }
}
