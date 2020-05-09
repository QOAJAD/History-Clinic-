package com.qoajad.backend.model.internal.log;

import com.qoajad.backend.utils.ValidationUtils;

import java.util.Date;
import java.util.Objects;

public class Log {

    private final int id;
    // If there is no active user id, then the following value must be set to -1.
    private final int activeUserId;
    private final String state;
    private final Date requestDate;
    private final String ip;
    private final Object data;
    private final String requestType;
    private final String eventType;

    public Log(int id, int activeUserId, String state, Date requestDate, String ip, Object data, String requestType) {
        this.id = id;
        this.activeUserId = activeUserId;
        this.state = state;
        this.requestDate = requestDate;
        this.ip = ip;
        this.data = data;
        this.requestType = requestType;
        this.eventType = data.getClass().getSimpleName();
        ValidationUtils.requireLeftGreaterThanRight(this.id, 0, "The log id must be positive");
        Objects.requireNonNull(this.state, "The state cannot be null.");
        Objects.requireNonNull(this.requestDate, "The date cannot be null.");
        Objects.requireNonNull(this.ip, "The ip cannot be null.");
        Objects.requireNonNull(this.data, "The data cannot be null.");
        Objects.requireNonNull(this.requestType, "The requestType cannot be null.");

    }

    public int getId() {
        return id;
    }

    public int getActiveUserId() {
        return activeUserId;
    }

    public String getState() {
        return state;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getIp() {
        return ip;
    }

    public Object getData() {
        return data;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getEventType() {
        return eventType;
    }
}
