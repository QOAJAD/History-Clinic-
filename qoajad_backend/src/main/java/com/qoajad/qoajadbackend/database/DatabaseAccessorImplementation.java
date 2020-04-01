package com.qoajad.qoajadbackend.database;

import com.qoajad.qoajadbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultDatabaseAccessor")
public class DatabaseAccessorImplementation implements DatabaseAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(jdbcTemplate, "To instantiate the database accessor the jdbc template must not be null.");
    }

    @Override
    public List<User> retrieveAllUsers() {
        final String query = "SELECT user_id, user_pw FROM User";
        List<User> users = null;
        try {
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            });
        } catch (EmptyResultDataAccessException exception) {
            exception.printStackTrace();
        }
        return users == null ? Collections.emptyList() : users;
    }
}
