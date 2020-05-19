package com.qoajad.backend.model.external.eps.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("mensaje")
    private String message;

    /**
     * Default constructor required for json so it doesn't fail instantiating the class.
     */
    public Response() { }

    public Response(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
