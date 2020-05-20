package com.qoajad.backend.model.internal.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class CreateUser {

    private final String username;
    private final String password;
    private final long document;

    public CreateUser(String username, String password, long document) {
        this.username = username;
        this.password = password;
        this.document = document;
        Objects.requireNonNull(this.username, "The username cannot be null.");
        Objects.requireNonNull(this.password, "The password cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive.");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getDocument() {
        return document;
    }
}
