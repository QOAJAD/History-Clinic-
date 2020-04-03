package com.qoajad.backend.database;

import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        List<User> users;
        try {
            final String query = "SELECT user_document, user_pw FROM User";
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_document");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users == null ? Collections.emptyList() : users;
    }

    @Override
    public User findUserById(int id) {
        User user;
        try {
            final String query = "SELECT user_document, user_pw FROM User where user_document = ?";
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_document");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public void createUser(int id, String password) {
        try {
            final String query = "INSERT INTO User(user_document, user_pw) VALUES (?, ?)";
            jdbcTemplate.update(query, id, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(int id, String password) {
        int rowsChanged;
        try {
            final String query = "UPDATE User SET user_pw = ? WHERE user_document = ?";
            rowsChanged = jdbcTemplate.update(query, password, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        int rowsChanged;
        try {
            final String query = "DELETE FROM User WHERE user_document = ?";
            rowsChanged = jdbcTemplate.update(query, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }
}
