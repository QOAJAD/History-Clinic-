package com.qoajad.backend.model.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class User {

    private final int id;
    private final String username;
    private final String password;
    private final int document;

    public User(final int id, final String username, final String password, final int document) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.document = document;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The user id must be positive.");
        Objects.requireNonNull(this.username, "The username cannot be null.");
        Objects.requireNonNull(this.password, "The password cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive");
    }

    public int getDocument() {
        return document;
    }

    public String getPassword() {
        return password;
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }
}
