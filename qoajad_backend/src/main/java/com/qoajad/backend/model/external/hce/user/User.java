package com.qoajad.backend.model.external.hce.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qoajad.backend.utils.ValidationUtils;

public class User {
    @JsonProperty("DNI")
    private long document;

    public User() {}

    public User(long document) {
        this.document = document;
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive.");
    }

    public long getDocument() {
        return document;
    }
}
