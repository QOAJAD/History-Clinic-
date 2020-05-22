package com.qoajad.backend.model.internal.user;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class UpdateUserHPE {

    private long document;
    private String healthPromotingEntityName;

    public UpdateUserHPE() {}

    public UpdateUserHPE(long document, String healthPromotingEntityName) {
        this.document = document;
        this.healthPromotingEntityName = healthPromotingEntityName;
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive.");
        Objects.requireNonNull(healthPromotingEntityName, "The health promoting entity name cannot be null.");
    }

    public long getDocument() {
        return document;
    }

    public String getHealthPromotingEntityName() {
        return healthPromotingEntityName;
    }
}
