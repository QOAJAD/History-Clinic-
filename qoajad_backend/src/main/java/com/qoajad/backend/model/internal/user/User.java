package com.qoajad.backend.model.internal.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class User {

    private int id;
    private String username;
    private long document;
    private int healthPromotingEntityId;

    public User() {
    }

    public User(final int id, final String username, final long document, int healthPromotingEntityId) {
        this.id = id;
        this.username = username;
        this.document = document;
        this.healthPromotingEntityId = healthPromotingEntityId;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The user id must be positive.");
        Objects.requireNonNull(this.username, "The username cannot be null.");
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive");
        ValidationUtils.requireLeftGreaterThanRight(this.healthPromotingEntityId, 0, "The health promoting entity id must be positive");

    }

    public long getDocument() {
        return document;
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }

    public int getHealthPromotingEntityId() {
        return healthPromotingEntityId;
    }
}
