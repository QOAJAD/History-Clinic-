package com.qoajad.backend.model.appointment;

import java.util.Date;
import java.util.Objects;

public class CreateAppointment {

    private final String scheduledBy;
    private final Date date;

    public CreateAppointment(final String scheduledBy, final Date date) {
        this.scheduledBy = scheduledBy;
        this.date = date;
        Objects.requireNonNull(this.scheduledBy, "The scheduled by field cannot be null.");
        Objects.requireNonNull(this.date, "The date field cannot be null.");
    }

    public String getScheduledBy() {
        return scheduledBy;
    }

    public Date getDate() {
        return date;
    }
}