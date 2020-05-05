package com.qoajad.backend.model.log;

public class AuthenticationLog {

    private final String username;

    public AuthenticationLog(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
