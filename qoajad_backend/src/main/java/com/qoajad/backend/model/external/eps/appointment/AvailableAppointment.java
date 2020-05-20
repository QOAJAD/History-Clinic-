package com.qoajad.backend.model.external.eps.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoajad.backend.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AvailableAppointment {

    private String doctorName;
    private int doctorDocument;
    // Format this date in the following way when sent to eps.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private Date date;

    public AvailableAppointment() {
    }

    public AvailableAppointment(final String doctorName, final int doctorDocument, final Date date) {
        this.doctorName = doctorName;
        this.doctorDocument = doctorDocument;
        this.date = date;
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
}
