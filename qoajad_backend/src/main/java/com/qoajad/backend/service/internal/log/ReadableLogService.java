package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.model.internal.log.Log;

import java.util.List;

public interface ReadableLogService {
    List<Log> retrieveAllLogs();
}
