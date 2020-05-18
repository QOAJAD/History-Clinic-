package com.qoajad.backend.model.external.specialty;


import java.util.List;

public class Specialties {

    private final String name;
    private final List<Specialty> specialties;

    public Specialties(String name, List<Specialty> specialties) {
        this.name = name;
        this.specialties = specialties;
    }

    public String getName() {
        return name;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }
}
