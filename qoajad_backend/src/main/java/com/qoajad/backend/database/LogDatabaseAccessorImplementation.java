package com.qoajad.backend.database;

import com.google.gson.Gson;
import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.appointment.log.CreateAppointmentLog;
import com.qoajad.backend.model.appointment.log.UpdateAppointmentLog;
import com.qoajad.backend.model.log.Log;
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
    public void log(final Log log) {
        try {
            final String query = "INSERT INTO Log(active_user_id, state, time, ip, data, requestType, eventType) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    query, log.getActiveUserId() == -1 ? null : log.getActiveUserId(), log.getState(),
                    dateFormatService.convertDateToMySQLDateTime(log.getRequestDate()),
                    log.getIp(), new Gson().toJson(log.getData()), log.getRequestType(), log.getEventType());
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
