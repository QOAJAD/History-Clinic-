package com.qoajad.backend.model.external.hce.user;

public class UpdateUser {

    private final int userId;
    private final String newPassword;

    public UpdateUser(final int userId, final String newPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
