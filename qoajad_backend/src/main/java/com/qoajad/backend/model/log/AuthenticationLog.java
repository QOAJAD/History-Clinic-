package com.qoajad.backend.model.log;

import java.util.Objects;

public class AuthenticationLog {

    private final String username;

    public AuthenticationLog(String username) {
        this.username = username;
        Objects.requireNonNull(this.username, "The username cannot be null.");
    }

    public String getUsername() {
        return username;
    }

}
