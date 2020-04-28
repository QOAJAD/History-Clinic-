package com.qoajad.backend.model.authentication;

public class PrimitiveUserDetail {

    private final String username;
    private final String password;

    public PrimitiveUserDetail(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
