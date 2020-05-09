package com.qoajad.backend.database;

import com.google.gson.Gson;
import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.model.internal.log.AuthenticationLog;
import com.qoajad.backend.model.internal.log.Log;
import com.qoajad.backend.service.internal.date.format.DateFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultLogDatabaseAccessor")
public class LogDatabaseAccessorImplementation implements LogAccessor {

    private final JdbcTemplate jdbcTemplate;
    private final DateFormatService dateFormatService;

    @Autowired
    public LogDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate, @Qualifier("defaultDateFormatService") final DateFormatService dateFormatService) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateFormatService = dateFormatService;
        Objects.requireNonNull(this.jdbcTemplate, "The JdbcTemplate cannot be passed as null when instantiating a database accessor.");
        Objects.requireNonNull(this.dateFormatService, "The DateFormatService cannot be passed as null when instantiating the LogDatabaseAccessor.");
    }

    @Override
    public void log(final Log log) {
        Objects.requireNonNull(log, "The log cannot be null.");
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

    @Override
    public List<Log> retrieveAllLogs() {
        List<Log> logs;
        try {
            final String query = "SELECT id, active_user_id, state, time, ip, data, requestType, eventType FROM Log";
            logs = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int id = resultSet.getInt("id");
                final int activeUserId = resultSet.getInt("active_user_id");
                final String state = resultSet.getString("state");
                Date time = null;
                try {
                    time = dateFormatService.convertMySQLDateTimeToDate(resultSet.getString("time"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final String ip = resultSet.getString("ip");
                Object data = resultSet.getString("data");
                final String requestType = resultSet.getString("requestType");
                final String eventType = resultSet.getString("eventType");
                switch(eventType) {
                    case "AuthenticationLog": data = new Gson().fromJson(data.toString(), AuthenticationLog.class);
                }
                return new Log(id, activeUserId, state, time, ip, data, requestType);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return logs;
    }
}
