package com.qoajad.backend.model.external.eps.appointment;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class Appointment {

    private final int id;
    private final Date date;
    private final String healthProviderInstitute;
    private final String address;
    private final String doctorName;
    private final String specialty;

    public Appointment(int id, Date date, String healthProviderInstitute, String address, String doctorName, String specialty) {
        this.id = id;
        this.date = date;
        this.healthProviderInstitute = healthProviderInstitute;
        this.address = address;
        this.doctorName = doctorName;
        this.specialty = specialty;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "Appointment id must be positive.");
        Objects.requireNonNull(this.date, "Date cannot be null.");
        Objects.requireNonNull(this.healthProviderInstitute, "Health provider institute cannot be null.");
        Objects.requireNonNull(this.address, "Address cannot be null.");
        Objects.requireNonNull(this.doctorName, "Doctor name cannot be null");
        Objects.requireNonNull(this.specialty, "Specialty cannot be null");
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getHealthProviderInstitute() {
        return healthProviderInstitute;
    }

    public String getAddress() {
        return address;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }
}
