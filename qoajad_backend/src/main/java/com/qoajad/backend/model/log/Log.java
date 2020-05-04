package com.qoajad.backend.model.log;

import java.util.Date;

public class Log {

    private final int id;
    private final int userId;
    private final String state;
    private final Date requestDate;
    private final String ip;
    private final String data;
    private final String requestType;

    public Log(int id, int userId, String state, Date requestDate, String ip, String data, String requestType) {
        this.id = id;
        this.userId = userId;
        this.state = state;
        this.requestDate = requestDate;
        this.ip = ip;
        this.data = data;
        this.requestType = requestType;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
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

    public String getData() {
        return data;
    }

    public String getRequestType() {
        return requestType;
    }
}
