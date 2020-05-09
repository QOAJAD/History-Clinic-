package com.qoajad.backend.model.external.appointment;

import com.google.gson.annotations.SerializedName;
import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class AvailableAppointment {

    @SerializedName("nombre doctor")
    private final String doctorName;
    @SerializedName("documento doctor")
    private final int doctorDocument;
    @SerializedName("fecha cita")
    private final Date date;

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
