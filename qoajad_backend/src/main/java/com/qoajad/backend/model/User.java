package com.qoajad.backend.model;

public class User {

    private final int id;
    private final String password;
    private final int document;

    public User(final int id, final String password, final int document) {
        this.id = id;
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
}
