package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.internal.user.CreateUser;
import com.qoajad.backend.model.internal.user.UpdateUser;
import com.qoajad.backend.model.internal.user.UpdateUserHPE;
import com.qoajad.backend.model.internal.user.User;
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
            final String query = "SELECT id, username, document, health_promoting_entity_id FROM User";
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int id = resultSet.getInt("id");
                final String username = resultSet.getString("username");
                final long document = resultSet.getLong("document");
                final int healthPromotingEntityId = resultSet.getInt("health_promoting_entity_id");
                return new User(id, username, document, healthPromotingEntityId);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    @Override
    public User findUserByUsername(String username) {
        Objects.requireNonNull(username, "The username cannot be null.");
        User user;
        try {
            final String query = "SELECT id, username, document, health_promoting_entity_id FROM User where username = ?";
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int id = resultSet.getInt("id");
                final long document = resultSet.getLong("document");
                final int healthPromotingEntityId = resultSet.getInt("health_promoting_entity_id");

                return new User(id, username, document, healthPromotingEntityId);
            }, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public void createUser(CreateUser user) {
        Objects.requireNonNull(user, "The user cannot be null.");
        try {
            final String query = "INSERT INTO User(username, document, pw, health_promoting_entity_id) VALUES (?, ?, ?, ?)";
            final String encryptedPassword = encryptPassword(user.getPassword());
            jdbcTemplate.update(query, user.getUsername(), user.getDocument(), encryptedPassword, 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(UpdateUser user, String username) {
        Objects.requireNonNull(user, "The updated user cannot be null.");
        Objects.requireNonNull(username, "The username cannot be null.");
        System.out.println("Username: " + username + " new usname : " + user.getUsername());
        int rowsChanged;
        try {
            final User localUser = findUserByUsername(username);
            final String localPassword = retrievePassword(username);
            final String query = "UPDATE User SET username = ?, pw = ? WHERE username = ?";
            rowsChanged = jdbcTemplate.update(query, (user.getUsername() == null || user.getUsername().isEmpty()) ? localUser.getUsername() : user.getUsername(),
                    (user.getPassword() == null || user.getPassword().isEmpty()) ? localPassword : encryptPassword(user.getPassword()), username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    private String encryptPassword(final String rawPassword) {
        return BCRYPT_PASSWORD_PREFIX + bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean deleteUser(String username) {
        Objects.requireNonNull(username, "The username cannot be null.");
        int rowsChanged;
        try {
            final String query = "DELETE FROM User WHERE username = ?";
            rowsChanged = jdbcTemplate.update(query, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    @Override
    public String retrievePassword(String username) {
        Objects.requireNonNull(username, "The username cannot be null.");
        final String query = "SELECT pw from User where username = ?";
        return jdbcTemplate.queryForObject(query, new Object [] {username}, String.class);
    }

    @Override
    public boolean updateUserHealthPromotingEntity(UpdateUserHPE updateUserHPE) {
        Objects.requireNonNull(updateUserHPE, "The user cannot be null.");
        int rowsChanged;
        try {
            final String query = "UPDATE User SET health_promoting_entity_id = (SELECT id FROM HealthPromotingEntity WHERE name = ?) WHERE document = ?";
            rowsChanged = jdbcTemplate.update(query, updateUserHPE.getHealthPromotingEntityName(), updateUserHPE.getDocument());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    @Override
    public String retrieveHPE(String username) {
        Objects.requireNonNull(username, "The user cannot be null.");
        final String query = "SELECT name FROM HealthPromotingEntity LEFT JOIN User ON User.health_promoting_entity_id = HealthPromotingEntity.id WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new Object [] {username}, String.class);
    }
}
