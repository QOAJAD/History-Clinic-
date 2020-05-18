package com.qoajad.backend.model.external.response;

import com.google.gson.annotations.SerializedName;

public class Response {

    private final String message;

    public Response(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
