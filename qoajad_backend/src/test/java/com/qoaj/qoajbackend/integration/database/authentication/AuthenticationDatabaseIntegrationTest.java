package com.qoaj.qoajbackend.integration.database.authentication;

import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.junit.MariaDB4jRule;
import com.qoajad.backend.database.AuthenticationDatabaseAccessorImplementation;
import com.qoajad.backend.database.accessor.AuthenticationAccessor;
import com.qoajad.backend.model.authentication.PrimitiveUserDetail;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;

import static utils.database.TestDatabaseUtils.*;

/**
 * Note: This requires a ssl configuration of openssl/1.0.2t to be run without errors.
 * This test may take some time due to the databaseRule being a rule and not a class rule but
 * this is required so the database is cleared after the test has finished.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationDatabaseIntegrationTest {

    private static final String DATABASE_SQL_FILE = "mysql-authentication-test.sql";

    @Rule
    public MariaDB4jRule databaseRule = new MariaDB4jRule(DBConfigurationBuilder.newBuilder().setPort(PORT).build(), DATABASE_NAME, DATABASE_SQL_FILE);

    @Test(expected = NullPointerException.class)
    public void testJdbcTemplateInAuthenticationAccessorCannotBePassedAsNull() {
        new AuthenticationDatabaseAccessorImplementation(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveUserDetailsWithNullUsernameThrowsNullPointerException() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final AuthenticationAccessor authenticationAccessor = new AuthenticationDatabaseAccessorImplementation(mockedJdbcTemplate);
        authenticationAccessor.retrieveUserDetails(null);
    }

    /**
     * This test validates that an existing user in the database (Thanks to the DATABASE_SQL_FILE)
     * is detected as an existing user and, as a consequence, computes correctly the PrimitiveUserDetails.
     */
    @Test
    public void testExistingUserExistsAndPrimitiveDetailsAreCorrect() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final AuthenticationAccessor authenticationAccessor = new AuthenticationDatabaseAccessorImplementation(mockedJdbcTemplate);
        final String username = "juan.2114@hotmail.com";
        final String nonEncryptedPassword = "juan546";
        final PrimitiveUserDetail primitiveUserDetail = authenticationAccessor.retrieveUserDetails(username);

        Assert.assertNotNull(primitiveUserDetail);
        Assert.assertEquals(primitiveUserDetail.getUsername(), username);
        Assert.assertEquals(primitiveUserDetail.getPassword(), nonEncryptedPassword);
    }

    /**
     * This test validates that a non existing user is not found in the database and, as a consequence, the
     * PrimitiveUserDetails are null.
     */
    @Test
    public void textNonExistingUserDoesntExistAndPrimitiveDetailsAreNull() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final AuthenticationAccessor authenticationAccessor = new AuthenticationDatabaseAccessorImplementation(mockedJdbcTemplate);
        final String nonExistingUsername = "non-existing@hotmail.com";
        final PrimitiveUserDetail primitiveUserDetail = authenticationAccessor.retrieveUserDetails(nonExistingUsername);

        Assert.assertNull(primitiveUserDetail);
    }
}
