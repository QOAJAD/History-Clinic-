package com.qoajad.backend.model.user;

public class UpdateUser {

    private final int id;
    private final String username;
    private final String password;
    private final int document;

    public UpdateUser(final int id, final String username, final String password, final int document) {
        this.id = id;
        this.password = password;
        this.document = document;
        this.username = username;
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

