package com.qoajad.backend.model.external.eps.appointment;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class AvailableAppointment {

    private final int id;
    private final String doctorName;
    private final int doctorDocument;
    private final Date date;

    public AvailableAppointment(int id, final String doctorName, final int doctorDocument, final Date date) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDocument = doctorDocument;
        this.date = date;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The id must be positive.");
        Objects.requireNonNull(this.doctorName, "The doctorName field cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.doctorDocument, 0, "The doctor document must be positive.");
        Objects.requireNonNull(this.date, "The date field cannot be null.");
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getDoctorDocument() {
        return doctorDocument;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
