package com.qoajad.backend.model.external.appointment;

import com.google.gson.annotations.SerializedName;
import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class Appointment {

    private final int id;

    @SerializedName("fecha")
    private final Date date;

    @SerializedName("ips")
    private final String healthProviderInstitute;

    @SerializedName("direccion")
    private final String address;

    @SerializedName("medico")
    private final String doctorName;

    @SerializedName("especializacion")
    private final String specialization;

    public Appointment(int id, Date date, String healthProviderInstitute, String address, String doctorName, String specialization) {
        this.id = id;
        this.date = date;
        this.healthProviderInstitute = healthProviderInstitute;
        this.address = address;
        this.doctorName = doctorName;
        this.specialization = specialization;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "Appointment id must be positive.");
        Objects.requireNonNull(this.date, "Date cannot be null.");
        Objects.requireNonNull(this.healthProviderInstitute, "Health provider institute cannot be null.");
        Objects.requireNonNull(this.address, "Address cannot be null.");
        Objects.requireNonNull(this.doctorName, "Doctor name cannot be null");
        Objects.requireNonNull(this.specialization, "Specialization cannot be null");
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

    public String getSpecialization() {
        return specialization;
    }
}