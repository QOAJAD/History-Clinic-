package com.qoajad.backend.model.external.hce.authentication;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class HealthPromotingEntityAuthentication {

    private final int id;
    private final String password;

    public HealthPromotingEntityAuthentication(final int id, final String password) {
        this.id = id;
        this.password = password;
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The health promoting entity id must be positive.");
        Objects.requireNonNull(this.password, "The health promoting entity password cannot be null.");
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
