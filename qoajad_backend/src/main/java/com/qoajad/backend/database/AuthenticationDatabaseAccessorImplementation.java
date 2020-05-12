package com.qoajad.backend.database;

import com.qoajad.backend.database.accessor.AuthenticationAccessor;
import com.qoajad.backend.model.authentication.PrimitiveUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("defaultAuthenticationDatabaseAccessor")
public class AuthenticationDatabaseAccessorImplementation implements AuthenticationAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthenticationDatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(this.jdbcTemplate, "The JdbcTemplate cannot be passed as null when instantiating a database accessor.");
    }

    @Override
    public PrimitiveUserDetail retrieveUserDetails(String username) {
        Objects.requireNonNull(username, "The username cannot be null.");
        final String query = "SELECT username, pw FROM User where username = ?";
        final PrimitiveUserDetail userDetail = jdbcTemplate.query(query, new Object[]{username}, (resultSet) -> {
            if (resultSet.next()) {
                final String password = resultSet.getString("pw");
                return new PrimitiveUserDetail(username, password);
            }
            return null;
        });
        return userDetail;
    }
}
