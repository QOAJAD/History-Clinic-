package com.qoajad.backend.database;

import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultDatabaseAccessor")
public class DatabaseAccessorImplementation implements DatabaseAccessor {

    private final JdbcTemplate jdbcTemplate;

    // TODO Create exception class to handle every catch on the CRUD functions in order to avoid duplicate code

    @Autowired
    public DatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(jdbcTemplate, "To instantiate the database accessor the jdbc template must not be null.");
    }

    @Override
    public ResponseEntity<List<User>> retrieveAllUsers() {
        final String query = "SELECT user_id, user_pw FROM User";
        List<User> users;
        ResponseEntity<List<User>> response;
        try {
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            });
            response = new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(e instanceof DataIntegrityViolationException) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<User> readUser(Integer id) {
        final String query = "SELECT user_id, user_pw FROM User where user_id = ?";
        User user = null;
        ResponseEntity<User> response;
        try {
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password);
            }, id);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                response = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
            }
            else if(e instanceof DataIntegrityViolationException) {
                response = new ResponseEntity<>(user, HttpStatus.CONFLICT);
            }
            else {
                response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<Void> createUser(Integer id, String password) {
        final String query = "INSERT INTO User(user_id, user_pw) VALUES (?, ?)";
        ResponseEntity<Void> response;
        try {
            jdbcTemplate.update(query, id, password);
            response = new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(e instanceof DataIntegrityViolationException) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<Void> updateUser(Integer id, String password) {
        final String query = "UPDATE User SET user_pw = ? WHERE user_id = ?";
        ResponseEntity<Void> response;
        try {
            jdbcTemplate.update(query, id, password);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(e instanceof DataIntegrityViolationException) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        final String query = "DELETE FROM User WHERE user_id = ?";
        ResponseEntity<Void> response;
        try {
            jdbcTemplate.update(query, id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(e instanceof DataIntegrityViolationException) {
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }
}
