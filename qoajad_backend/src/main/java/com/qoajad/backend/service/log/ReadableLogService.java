package com.qoajad.backend.service.log;

import com.qoajad.backend.model.log.Log;

import java.util.List;

public interface ReadableLogService {
    List<Log> retrieveAllLogs();
}
