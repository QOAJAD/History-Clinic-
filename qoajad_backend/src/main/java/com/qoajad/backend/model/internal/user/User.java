package com.qoajad.backend.model.internal.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class User {

    private final int id;
    private final String username;
    private final long document;

    public User(final int id, final String username, final long document) {
        this.id = id;
        this.username = username;
        this.document = document;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The user id must be positive.");
        Objects.requireNonNull(this.username, "The username cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive");
    }

    public long getDocument() {
        return document;
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }
}
