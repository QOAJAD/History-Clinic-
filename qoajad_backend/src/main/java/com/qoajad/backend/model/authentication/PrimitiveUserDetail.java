package com.qoajad.backend.model.authentication;

import java.util.Objects;

public class PrimitiveUserDetail {

    private final String username;
    private final String password;

    public PrimitiveUserDetail(final String username, final String password) {
        this.username = username;
        this.password = password;
        Objects.requireNonNull(this.username, "The username cannot be null.");
        Objects.requireNonNull(this.password, "The password cannot be null.");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
