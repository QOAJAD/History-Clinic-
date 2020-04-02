package com.qoajad.backend.model;

public class User {

    private final int id;
    private final String password;

    public User(final int id, final String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
