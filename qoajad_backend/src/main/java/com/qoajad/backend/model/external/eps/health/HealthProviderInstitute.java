package com.qoajad.backend.model.external.eps.health;

public class HealthProviderInstitute {

    private final int id;
    private final String name;
    private final String streetAddress;

    public HealthProviderInstitute(final int id, final String name, final String streetAddress) {
        this.id = id;
        this.name = name;
        this.streetAddress = streetAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }
}
