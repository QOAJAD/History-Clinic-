package com.qoajad.backend.model.external.eps.specialty;

public class Specialties {

    private String name;
    private Specialty[] specialties;

    public Specialties() {
    }

    public Specialties(String name, Specialty[] specialties) {
        this.name = name;
        this.specialties = specialties;
    }

    public String getName() {
        return name;
    }

    public Specialty[] getSpecialties() {
        return specialties;
    }
}
