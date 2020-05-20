package com.qoajad.backend.model.external.hce.user;

public class UserResponse {

    private final String status;
    private final String data;

    public UserResponse(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
