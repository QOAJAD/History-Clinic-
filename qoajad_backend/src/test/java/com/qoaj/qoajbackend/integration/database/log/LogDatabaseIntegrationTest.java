package com.qoaj.qoajbackend.integration.database.log;


import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.junit.MariaDB4jRule;
import com.qoajad.backend.database.LogDatabaseAccessorImplementation;
import com.qoajad.backend.database.accessor.LogAccessor;
import com.qoajad.backend.service.date.format.DateFormatService;
import com.qoajad.backend.service.date.format.DateFormatServiceImplementation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class LogDatabaseIntegrationTest {

    private static final String DATABASE_SQL_FILE = "mysql-user-test.sql";

    @Rule
    public MariaDB4jRule databaseRule = new MariaDB4jRule(DBConfigurationBuilder.newBuilder().setPort(PORT).build(), DATABASE_NAME, DATABASE_SQL_FILE);

    /**
     * This rule is used for handling exceptions during a test and asserting over those exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = NullPointerException.class)
    public void testJdbcTemplateInLogDatabaseAccessorCannotBePassedAsNull1() {
        new LogDatabaseAccessorImplementation(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testJdbcTemplateInLogDatabaseAccessorCannotBePassedAsNull2() {
        new LogDatabaseAccessorImplementation(null, new DateFormatServiceImplementation());
    }

    @Test(expected = NullPointerException.class)
    public void testJdbcTemplateInLogDatabaseAccessorCannotBePassedAsNull3() throws SQLException {
        new LogDatabaseAccessorImplementation(createMockedJdbcTemplate(databaseRule.getURL()), null);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateLogWithNullUserThrowsException() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final DateFormatService dateFormatService = new DateFormatServiceImplementation();
        final LogAccessor logAccessor = new LogDatabaseAccessorImplementation(mockedJdbcTemplate, dateFormatService);

        logAccessor.log(null);
    }
}
