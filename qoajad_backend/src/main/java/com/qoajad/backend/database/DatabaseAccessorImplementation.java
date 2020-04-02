package com.qoajad.backend.database;

import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
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
            final String query = "SELECT user_id, user_pw FROM User";
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            });
        } catch (Exception e) {
            throw e;
        }
        return users;
    }

    @Override
    public User readUser(int id) {
        User user;
        try {
            final String query = "SELECT user_id, user_pw FROM User where user_id = ?";
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            }, id);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    @Override
    public Void createUser(int  id, String password) {
        try {
            final String query = "INSERT INTO User(user_id, user_pw) VALUES (?, ?)";
            jdbcTemplate.update(query, id, password);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    @Override
    public int updateUser(int id, String password) {
        int rowsChanged = 0;
        try {
            final String query = "UPDATE User SET user_pw = ? WHERE user_id = ?";
            rowsChanged = jdbcTemplate.update(query, password, id);
        } catch (Exception e) {
            throw e;
        }
        return rowsChanged;
    }

    @Override
    public int deleteUser(int id) {
        int rowsChanged = 0;
        try {
            final String query = "DELETE FROM User WHERE user_id = ?";
            rowsChanged = jdbcTemplate.update(query, id);
        } catch (Exception e) {
            throw e;
        }
        return rowsChanged;
    }
}
