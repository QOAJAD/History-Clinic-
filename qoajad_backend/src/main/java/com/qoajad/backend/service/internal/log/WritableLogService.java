package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.model.internal.log.LogCreate;

public interface WritableLogService {

    void log(final LogCreate logCreate);
}
