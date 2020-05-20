package com.qoajad.backend.model.external.eps.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAppointmentResponse {

    @JsonProperty("new id")
    private String appointmentId;
    private String code;

    public CreateAppointmentResponse() {
    }

    public CreateAppointmentResponse(final String appointmentId, final String code) {
        this.appointmentId = appointmentId;
        this.code = code;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getCode() {
        return code;
    }
}
