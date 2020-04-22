package com.qoajad.backend.model.appointment.log;

import java.util.Date;

public class UpdateAppointmentLog {

    private final int userId;
    private final int state;
    private final Date requestDate;
    private final String ip;
    private final Date appointmentDate;
    private final int doctorId;

    public UpdateAppointmentLog(int userId, int state, Date requestDate, String ip, Date appointmentDate, int doctorId) {
        this.userId = userId;
        this.state = state;
        this.requestDate = requestDate;
        this.ip = ip;
        this.appointmentDate = appointmentDate;
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public int getState() {
        return state;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getIp() {
        return ip;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
