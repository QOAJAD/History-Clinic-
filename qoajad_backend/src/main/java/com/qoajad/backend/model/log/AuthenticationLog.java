package com.qoajad.backend.model.log;

public class AuthenticationLog {

    private final String username;
    private final String eventName;

    public AuthenticationLog(String username) {
        this.username = username;
        this.eventName = getClass().getSimpleName();
    }

    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }
}
