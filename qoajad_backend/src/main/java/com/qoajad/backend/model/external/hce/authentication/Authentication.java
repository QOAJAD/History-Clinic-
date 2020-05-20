package com.qoajad.backend.model.external.hce.authentication;

public class Authentication {
    private final long id;
    private final String password;

    public Authentication(long id, String password) {
        this.id = id;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
