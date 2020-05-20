package com.qoajad.backend.model.external.eps.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qoajad.backend.utils.ValidationUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Appointment {

    private final int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm:ss a", timezone = "America/Jamaica")
    private Date date;
    private final String healthProviderInstitute;
    private final String address;
    private final String doctorName;

    private final String specialty;

    public Appointment(int id, String date, String healthProviderInstitute, String address, String doctorName, String specialization) {
        this.id = id;
        // This had to be hardcoded due to json property not being able to parse this date.
        final DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy (HH:mm:ss)");
        try {
            this.date = dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.healthProviderInstitute = healthProviderInstitute;
        this.address = address;
        this.doctorName = doctorName;
        this.specialty = specialization;
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
