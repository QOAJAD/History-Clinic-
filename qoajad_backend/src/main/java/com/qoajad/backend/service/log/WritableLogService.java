package com.qoajad.backend.service.log;

import com.qoajad.backend.model.log.Log;

public interface WritableLogService {

    void log(final Log log);
}
