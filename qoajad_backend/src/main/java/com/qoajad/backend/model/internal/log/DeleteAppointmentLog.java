package com.qoajad.backend.model.internal.log;

public class DeleteAppointmentLog {
    private final int id;

    public DeleteAppointmentLog(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
