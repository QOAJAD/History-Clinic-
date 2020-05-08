package com.qoajad.backend.model.external.health;

import com.google.gson.annotations.SerializedName;

public class HealthProviderInstitute {

    private final int id;

    @SerializedName("nombre")
    private final String name;

    @SerializedName("direccion")
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
