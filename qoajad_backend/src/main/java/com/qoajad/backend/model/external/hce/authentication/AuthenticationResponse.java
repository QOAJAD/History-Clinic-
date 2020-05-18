package com.qoajad.backend.model.external.hce.authentication;

import java.util.Objects;

public class AuthenticationResponse {
    private final String status;
    private final String message;
    private final String token;

    public AuthenticationResponse(String status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
        Objects.requireNonNull(this.status, "The status cannot be null.");
        Objects.requireNonNull(this.message, "The message cannot be null.");
        Objects.requireNonNull(this.token, "The token cannot be null.");
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
