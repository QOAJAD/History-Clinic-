package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.model.internal.log.Log;

public interface WritableLogService {

    void log(final Log log);
}
