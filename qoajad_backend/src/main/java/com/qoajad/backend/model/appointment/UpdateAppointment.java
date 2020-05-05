package com.qoajad.backend.model.appointment;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class UpdateAppointment {

    private final int id;
    private final String scheduledBy;
    private final Date date;

    public UpdateAppointment(final int id, final String scheduledBy, final Date date) {
        this.id = id;
        this.scheduledBy = scheduledBy;
        this.date = date;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The appointment id must be positive.");
        Objects.requireNonNull(this.scheduledBy, "The scheduled by field cannot be null.");
        Objects.requireNonNull(this.date, "The date field cannot be null.");
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
