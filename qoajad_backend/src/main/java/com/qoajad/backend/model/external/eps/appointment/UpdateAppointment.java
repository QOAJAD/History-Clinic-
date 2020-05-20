package com.qoajad.backend.model.external.eps.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoajad.backend.utils.ValidationUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UpdateAppointment {

    private int id;
    private int patientDocument;
    // Format this date in the following way when sent to eps.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm:ss a")
    private Date date;
    private int doctorDocument;
    private String healthProviderInstituteName;

    public UpdateAppointment(int id, int patientDocument, String date, int doctorDocument, String healthProviderInstituteName) {
        this.id = id;
        this.patientDocument = patientDocument;
        // This had to be hardcoded due to json property not being able to parse this date.
        final DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        try {
            this.date = dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.doctorDocument = doctorDocument;
        this.healthProviderInstituteName = healthProviderInstituteName;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "Appointment id must be positive.");
        ValidationUtils.requireLeftGreaterThanRight(this.patientDocument, 0, "Patient document must be positive.");
        Objects.requireNonNull(this.date, "Date cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.doctorDocument, 0, "Doctor document must be positive.");
        Objects.requireNonNull(this.healthProviderInstituteName, "Health provider institute cannot be null.");
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

    public String getHealthProviderInstituteName() {
        return healthProviderInstituteName;
    }
}
