package com.qoajad.backend.model.internal.authentication;

public class AuthenticationResponse {
    private final String response;
    private final String jwt;

    public AuthenticationResponse(String response, String jwt) {
        this.response = response;
        this.jwt = jwt;
    }

    public String getResponse() {
        return response;
    }

    public String getJwt() {
        return jwt;
    }
}
