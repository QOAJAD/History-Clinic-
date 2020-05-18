package com.qoajad.backend.model.external.eps.appointment;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class UpdateAppointment {

    private final int id;
    private final int patientDocument;
    private final Date date;
    private final int doctorDocument;
    private final String healthProviderInstitute;

    public UpdateAppointment(int id, int patientDocument, Date date, int doctorDocument, String healthProviderInstitute) {
        this.id = id;
        this.patientDocument = patientDocument;
        this.date = date;
        this.doctorDocument = doctorDocument;
        this.healthProviderInstitute = healthProviderInstitute;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "Appointment id must be positive.");
        ValidationUtils.requireLeftGreaterThanRight(this.patientDocument, 0, "Patient document must be positive.");
        Objects.requireNonNull(this.date, "Date cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.doctorDocument, 0, "Doctor document must be positive.");
        Objects.requireNonNull(this.healthProviderInstitute, "Health provider institute cannot be null.");
    }

    public int getId() {
        return id;
    }

    public int getPatientDocument() {
        return patientDocument;
    }

    public Date getDate() {
        return date;
    }

    public int getDoctorDocument() {
        return doctorDocument;
    }

    public String getHealthProviderInstitute() {
        return healthProviderInstitute;
    }
}
