package com.qoajad.backend.model.internal.authentication;

public class AuthenticationJWT {

    private final String jwt;
    private final String response;

    public AuthenticationJWT(String jwt, String response) {
        this.jwt = jwt;
        this.response = response;
    }

    public String getJwt() {
        return jwt;
    }

    public String getResponse() {
        return response;
    }
}
