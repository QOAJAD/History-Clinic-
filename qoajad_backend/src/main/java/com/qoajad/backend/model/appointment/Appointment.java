package com.qoajad.backend.model.appointment;

import java.util.Date;

public class Appointment {

    private final int id;
    private final String scheduledBy;
    private final Date date;

    public Appointment(final int id, final String scheduledBy, final Date date) {
        this.id = id;
        this.scheduledBy = scheduledBy;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getScheduledBy() {
        return scheduledBy;
    }

    public Date getDate() {
        return date;
    }
}
