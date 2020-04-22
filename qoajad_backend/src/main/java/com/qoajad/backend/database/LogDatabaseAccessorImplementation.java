package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.service.date.format.DateFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "defaultLogDatabaseAccessor")
public class LogDatabaseAccessorImplementation implements LogAccessor {

    private final JdbcTemplate jdbcTemplate;
    private final DateFormatService dateFormatService;

    @Autowired
    public LogDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate, @Qualifier("defaultDateFormatService") final DateFormatService dateFormatService) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateFormatService = dateFormatService;
    }

    @Override
    public void logAppointmentCreation(CreateAppointmentLog createAppointmentLog) {
        final String query = "INSERT INTO AppointmentLog (user_id, ap_state, ap_time, ap_ip, ap_date, ap_mdid) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, createAppointmentLog.getUserId(), createAppointmentLog.getState(),
                dateFormatService.convertDateToMySQLDateTime(createAppointmentLog.getRequestDate()), createAppointmentLog.getIp(),
                dateFormatService.convertDateToMySQLDateTime(createAppointmentLog.getAppointmentDate()), createAppointmentLog.getDoctorId());
    }

    @Override
    public void logAppointmentUpdate(UpdateAppointmentLog updateAppointmentLog) {
        final String query = "UPDATE AppointmentLog SET user_id = ?, ap_state = ?, ap_time = ?, ap_ip = ?, ap_date = ?, ap_mdid = ?";
        jdbcTemplate.update(query, updateAppointmentLog.getUserId(), updateAppointmentLog.getState(),
                dateFormatService.convertDateToMySQLDateTime(updateAppointmentLog.getRequestDate()), updateAppointmentLog.getIp(),
                dateFormatService.convertDateToMySQLDateTime(updateAppointmentLog.getAppointmentDate()), updateAppointmentLog.getDoctorId());
    }
}
