package com.qoajad.backend.model.external.eps.appointment;

import java.util.Objects;

public class ConsultingRoom {

    private final String name;
    private final AvailableAppointment[] availableAppointment;

    public ConsultingRoom(final String name, final AvailableAppointment[] availableAppointment) {
        this.name = name;
        this.availableAppointment = availableAppointment;
        Objects.requireNonNull(this.name, "The consulting room name cannot be null.");
        Objects.requireNonNull(this.availableAppointment, "Available appointments cannot be null.");
    }

    public String getName() {
        return name;
    }

    public AvailableAppointment[] getAvailableAppointment() {
        return availableAppointment;
    }
}
