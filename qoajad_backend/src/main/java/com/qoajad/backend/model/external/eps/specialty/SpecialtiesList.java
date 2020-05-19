package com.qoajad.backend.model.external.eps.specialty;

import java.util.List;

/**
 * This wrapper is required so json can deserialize the list of specialties in an object.
 */
public class SpecialtiesList {

    private final List<Specialties> specialtiesList;

    public SpecialtiesList(List<Specialties> specialtiesList) {
        this.specialtiesList = specialtiesList;
    }

    public List<Specialties> getSpecialtiesList() {
        return specialtiesList;
    }
}
