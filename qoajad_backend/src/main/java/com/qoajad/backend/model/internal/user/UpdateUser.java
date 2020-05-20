package com.qoajad.backend.model.internal.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class UpdateUser {

    private String username;
    private String password;
    private long document;

    public UpdateUser() {
    }

    public UpdateUser(final String username, final String password, final long document) {
        this.username = username;
        this.password = password;
        this.document = document;
        Objects.requireNonNull(this.username, "The username cannot be null.");
        Objects.requireNonNull(this.password, "The password cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive.");
    }

    public long getDocument() {
        return document;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

