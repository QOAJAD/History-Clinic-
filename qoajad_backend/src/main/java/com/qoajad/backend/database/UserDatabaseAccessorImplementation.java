package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.user.UpdateUser;
import com.qoajad.backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultUserDatabaseAccessor")
public class UserDatabaseAccessorImplementation implements UserAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(jdbcTemplate, "To instantiate the database accessor the jdbc template must not be null.");
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users;
        try {
            final String query = "SELECT id, username, document, pw FROM User";
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int id = resultSet.getInt("id");
                final String username = resultSet.getString("username");
                final int document = resultSet.getInt("document");
                final String password = resultSet.getString("pw");
                return new User(id, username, password, document);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    @Override
    public User findUserByDocument(int document) {
        User user;
        try {
            final String query = "SELECT id, username, document, pw FROM User where document = ?";
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int id = resultSet.getInt("id");
                final String username = resultSet.getString("username");
                final String password = resultSet.getString("pw");
                return new User(id, username, password, document);
            }, document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        try {
            final String query = "INSERT INTO User(username, document, pw) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, user.getUsername(), user.getDocument(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(UpdateUser user) {
        int rowsChanged;
        try {
            final String query = "UPDATE User SET username = ?, document = ?, pw = ? WHERE id = ?";
            rowsChanged = jdbcTemplate.update(query, user.getUsername(), user.getDocument(), user.getPassword(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean deleteUser(int document) {
        int rowsChanged;
        try {
            final String query = "DELETE FROM User WHERE document = ?";
            rowsChanged = jdbcTemplate.update(query, document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }
}
