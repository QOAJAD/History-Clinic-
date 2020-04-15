package com.qoajad.backend.model;

public class UpdateUser {

    private final int id;
    private final String password;
    private final int oldDocument;
    private final int newDocument;

    public UpdateUser(final int id, final String password, final int oldDocument, final int newDocument) {
        this.id = id;
        this.password = password;
        this.oldDocument = oldDocument;
        this.newDocument = newDocument;
    }

    public int getNewDocument() {
        return newDocument;
    }

    public int getOldDocument() {
        return oldDocument;
    }

    public String getPassword() {
        return password;
    }

    public int getId() { return id; }
}

