package com.qoajad.backend.service.log;

import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.model.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class that is based on the mediator pattern. This class acts as a mediator between the LogService and the outer world.
 * The reason to use this class is to provide a declarative way to log events into our database with minimum overhead.
 * This class uses multi threading to execute all the mediated actions.
 * @Note You must guarantee that every function being mediated calls execute to keep the functions as asynchronous.
 */
@Service
@Qualifier("asynchronousLogService")
public class AsynchronousLogServiceImplementation implements LogService {

    private static final int DEFAULT_THREADS = 1;

    private final LogService logService;
    private final ExecutorService executorService;

    @Autowired
    public AsynchronousLogServiceImplementation(@Qualifier("defaultLogService") final LogService logService) {
        this.logService = logService;
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREADS);
    }

    @Override
    public void logUserAuthentication(Log log) {
        execute(() ->{
            logService.logUserAuthentication(log);
        });
    }

    private void execute(final Runnable runnable) {
        executorService.execute(runnable);
    }
}
