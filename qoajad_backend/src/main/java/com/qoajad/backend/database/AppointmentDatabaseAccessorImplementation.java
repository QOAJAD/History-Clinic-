package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.AppointmentAccessor;
import com.qoajad.backend.model.appointment.Appointment;
import com.qoajad.backend.model.appointment.CreateAppointment;
import com.qoajad.backend.model.appointment.UpdateAppointment;
import com.qoajad.backend.service.date.format.DateFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@Qualifier("defaultAppointmentDatabaseAccessor")
public class AppointmentDatabaseAccessorImplementation implements AppointmentAccessor {

    private final JdbcTemplate jdbcTemplate;
    private final DateFormatService dateFormatService;

    @Autowired
    public AppointmentDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate, @Qualifier("defaultDateFormatService") final DateFormatService dateFormatService) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateFormatService = dateFormatService;
    }

    @Override
    public Appointment findAppointment(int appointmentId) {
        final String query = "SELECT id, scheduled_by, date FROM Appointment WHERE id = ?";
        Appointment appointment;
        try {
            appointment = jdbcTemplate.query(query, new Object[]{appointmentId}, (resultSet) -> {
                if (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String scheduledBy = resultSet.getString("scheduled_by");
                    Date date = null;
                    try {
                        date = dateFormatService.convertMySQLDateTimeToDate(resultSet.getString("date"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new Appointment(id, scheduledBy, date);
                }
                throw new EmptyResultDataAccessException(1);
            });
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return appointment;
    }

    @Override
    public boolean createAppointment(CreateAppointment appointment) {
        final String query = "INSERT INTO Appointment (scheduled_by, date) VALUES (?, ?)";
        int rowsAdded = jdbcTemplate.update(query, appointment.getScheduledBy(), dateFormatService.convertDateToMySQLDateTime(appointment.getDate()));
        return rowsAdded > 0;
    }

    @Override
    public boolean updateAppointment(UpdateAppointment appointment) {
        final String query = "UPDATE Appointment SET scheduled_by = ?, date = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(query, appointment.getScheduledBy(), dateFormatService.convertDateToMySQLDateTime(appointment.getDate()), appointment.getId());
        return rowsUpdated > 0;
    }
}
