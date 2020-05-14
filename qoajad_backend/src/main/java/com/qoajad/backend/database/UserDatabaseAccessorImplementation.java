package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.User;
import com.qoajad.backend.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultUserDatabaseAccessor")
public class UserDatabaseAccessorImplementation implements UserAccessor {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static final String BCRYPT_PASSWORD_PREFIX = "{bcrypt}";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(this.jdbcTemplate, "The JdbcTemplate cannot be passed as null when instantiating a database accessor.");
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
        ValidationUtils.requireLeftGreaterThanRight(document, 0, "The document must be positive.");
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
        Objects.requireNonNull(user, "The user cannot be null.");
        try {
            final String query = "INSERT INTO User(username, document, pw) VALUES (?, ?, ?)";
            final String encryptedPassword = BCRYPT_PASSWORD_PREFIX + bCryptPasswordEncoder.encode(user.getPassword());
            jdbcTemplate.update(query, user.getUsername(), user.getDocument(), encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(UpdateUser user) {
        Objects.requireNonNull(user, "The updated user cannot be null.");
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
        ValidationUtils.requireLeftGreaterThanRight(document, 0, "The document must be positive.");
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
