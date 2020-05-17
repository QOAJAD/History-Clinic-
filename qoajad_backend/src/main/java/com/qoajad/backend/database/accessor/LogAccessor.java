package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.internal.log.Log;
import com.qoajad.backend.model.internal.log.LogCreate;

import java.util.List;

public interface LogAccessor {

    void log(final LogCreate logCreate);
    List<Log> retrieveAllLogs();
}
