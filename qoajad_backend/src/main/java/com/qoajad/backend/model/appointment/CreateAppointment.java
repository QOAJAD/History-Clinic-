package com.qoajad.backend.model.appointment;

import java.util.Date;

public class CreateAppointment {

    private final String scheduledBy;
    private final Date date;

    public CreateAppointment(final String scheduledBy, final Date date) {
        this.scheduledBy = scheduledBy;
        this.date = date;
    }

    public String getScheduledBy() {
        return scheduledBy;
    }

    public Date getDate() {
        return date;
    }
}