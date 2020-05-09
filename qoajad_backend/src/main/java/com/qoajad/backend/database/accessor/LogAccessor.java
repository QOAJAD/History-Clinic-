package com.qoajad.backend.database.accessor;

import com.qoajad.backend.model.internal.log.Log;

import java.util.List;

public interface LogAccessor {

    void log(final Log log);
    List<Log> retrieveAllLogs();
}
