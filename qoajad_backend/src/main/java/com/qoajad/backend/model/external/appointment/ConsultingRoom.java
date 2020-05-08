package com.qoajad.backend.model.external.appointment;

import java.util.List;
import java.util.Objects;

public class ConsultingRoom {

    private final String name;
    private final List<AvailableAppointment> availableAppointments;

    public ConsultingRoom(final String name, final List<AvailableAppointment> availableAppointments) {
        this.name = name;
        this.availableAppointments = availableAppointments;
        Objects.requireNonNull(this.name, "The consulting room name cannot be null.");
        Objects.requireNonNull(this.availableAppointments, "Available appointments cannot be null.");
    }

    public String getName() {
        return name;
    }

    public List<AvailableAppointment> getAvailableAppointments() {
        return availableAppointments;
    }
}
