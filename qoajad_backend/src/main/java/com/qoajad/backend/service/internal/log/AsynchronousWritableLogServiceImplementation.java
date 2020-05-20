package com.qoajad.backend.service.internal.log;

import com.qoajad.backend.model.internal.log.LogCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class that is based on the mediator pattern. This class acts as a mediator between the WritableLogService and the outer world.
 * The reason to use this class is to provide a declarative way to log events into our database with minimum overhead.
 * This class uses multi threading to execute all the mediated actions.
 * @Note You must guarantee that every function being mediated calls execute to keep the functions as asynchronous.
 */
@Service
@Qualifier("asynchronousWritableLogService")
public class AsynchronousWritableLogServiceImplementation implements WritableLogService {

    private static final int DEFAULT_THREADS = 1;

    private final WritableLogService writableLogService;
    private final ExecutorService executorService;

    @Autowired
    public AsynchronousWritableLogServiceImplementation(@Qualifier("synchronousWritableLogService") final WritableLogService writableLogService) {
        this.writableLogService = writableLogService;
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREADS);
    }

    @Override
    public void log(LogCreate logCreate) {
        execute(() ->{
            writableLogService.log(logCreate);
        });
    }

    private void execute(final Runnable runnable) {
        executorService.execute(runnable);
    }
}
