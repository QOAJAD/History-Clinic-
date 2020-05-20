package com.qoajad.backend.model.external.hce.user;

public class UpdateUserResponse {

    private final String status;
    private final String message;

    public UpdateUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
