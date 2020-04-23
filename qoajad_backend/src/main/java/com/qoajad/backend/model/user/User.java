package com.qoajad.backend.model.user;

public class User {

    private final int id;
    private final String username;
    private final String password;
    private final int document;

    public User(final int id, final String username, final String password, final int document) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.document = document;
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
