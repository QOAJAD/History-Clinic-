package com.qoajad.backend.model.external.hce.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qoajad.backend.rpc.hce.HCEFeignInterceptor;
import com.qoajad.backend.utils.ValidationUtils;

import java.util.Objects;

public class UpdateUser {

    @JsonProperty("DNI")
    private final long document;
    private final String newPassword;

    /**
     * This field is required by the hce service.
     */
    @JsonProperty("idEntidad")
    private final int entityId = HCEFeignInterceptor.HEALTH_PROMOTING_ENTITY_ID;

    public UpdateUser(final long document, final String newPassword) {
        this.document = document;
        this.newPassword = newPassword;
        ValidationUtils.requireLeftGreaterThanRight(this.document, 0, "The document must be positive.");
        Objects.requireNonNull(newPassword, "The new password cannot be null.");
    }

    public long getDocument() {
        return document;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public int getEntityId() {
        return entityId;
    }
}
