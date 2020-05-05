package com.qoaj.qoajbackend.integration.database.user;

import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.junit.MariaDB4jRule;
import com.qoajad.backend.database.UserDatabaseAccessorImplementation;
import com.qoajad.backend.database.accessor.UserAccessor;
import com.qoajad.backend.model.user.UpdateUser;
import com.qoajad.backend.model.user.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

import static utils.database.TestDatabaseUtils.*;
import static utils.database.TestDatabaseUtils.createMockedJdbcTemplate;

/**
 * Note: This requires a ssl configuration of openssl/1.0.2t to be run without errors.
 * This test may take some time due to the databaseRule being a rule and not a class rule but
 * this is required so the database is cleared after the test has finished.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDatabaseIntegrationTest {

    private static final String DATABASE_SQL_FILE = "mysql-user-test.sql";

    @Rule
    public MariaDB4jRule databaseRule = new MariaDB4jRule(DBConfigurationBuilder.newBuilder().setPort(PORT).build(), DATABASE_NAME, DATABASE_SQL_FILE);

    /**
     * This rule is used for handling exceptions during a test and asserting over those exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateUserWorks() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        // Create the user in the database.
        final int document = 178495433;
        final String username = "andrea@timaran.com";
        final String password = "andrea321";
        final User user = new User(2, username, password, document);

        userAccessor.createUser(user);

        // Retrieve it from the database and validate it has been created.
        final User userRetrieved = userAccessor.findUserByDocument(document);
        Assert.assertNotNull(userRetrieved);
        Assert.assertEquals(userRetrieved.getUsername(), username);
        Assert.assertEquals(userRetrieved.getPassword(), password);
    }

    @Test
    public void testUpdateDefaultUserInScriptWorks() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        // Update the user id in the database.
        final int defaultUserId = 1;
        final String newUsername = "juan.2114@hotmail.com";
        final String newPassword = "password123";
        final int newDocument = 321874569;

        Assert.assertTrue(userAccessor.updateUser(new UpdateUser(defaultUserId, newUsername, newPassword, newDocument)));

        // Retrieve the user id from the database and validate it changed.
        final User updatedUser = userAccessor.findUserByDocument(newDocument);
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(updatedUser.getUsername(), newUsername);
        Assert.assertEquals(updatedUser.getPassword(), newPassword);
        Assert.assertEquals(updatedUser.getDocument(), newDocument);
    }

    @Test
    public void testUpdateNonExistingUserReturnsFalse() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        final int nonExistingUserId = 38;
        Assert.assertFalse(userAccessor.updateUser(new UpdateUser(nonExistingUserId, "juan.2114@hotmail.com", "password123", 321874569)));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteDefaultUseWorks() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        // Delete the user from the database.
        final int defaultUserDocument = 1144099495;
        Assert.assertNotNull(userAccessor.findUserByDocument(defaultUserDocument));

        Assert.assertTrue(userAccessor.deleteUser(defaultUserDocument));

        // Expect an empty result data exception.
        // Validate that the user is actually deleted.
        Assert.assertNull(userAccessor.findUserByDocument(defaultUserDocument));
    }

    @Test
    public void testDeleteNonExistingUserReturnsFalse() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        // Attempt to delete a non existing user.
        final int document = 16748994;
        // Expect an exception.
        thrown.expect(EmptyResultDataAccessException.class);
        userAccessor.findUserByDocument(document);

        // Validate we can't delete him.
        Assert.assertFalse(userAccessor.deleteUser(document));
    }

    @Test
    public void testFindUserByDocumentWithDefaultUser() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        final int defaultUserDocument = 1144099495;
        final User defaultUser = userAccessor.findUserByDocument(defaultUserDocument);
        Assert.assertNotNull(defaultUser);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindNonExistingUserThrowsException() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        final int nonExistingUserDocument = 1855492;
        userAccessor.findUserByDocument(nonExistingUserDocument);
    }

    /**
     * This test validates that the retrieval of all users with the default database script is 1.
     */
    @Test
    public void testRetrieveAllUsersSizeWithDefaultScriptIsOne() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);
        final List<User> users = userAccessor.retrieveAllUsers();

        Assert.assertNotNull(users);
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void testRetrieveAllUsersSizeWhenAddedANewUserIsTwo() throws SQLException {
        final JdbcTemplate mockedJdbcTemplate = createMockedJdbcTemplate(databaseRule.getURL());
        final UserAccessor userAccessor = new UserDatabaseAccessorImplementation(mockedJdbcTemplate);

        Assert.assertEquals(userAccessor.retrieveAllUsers().size(), 1);

        userAccessor.createUser(new User(1, "pedro-ortega@gmail.com", "pedro874", 446985413));
        Assert.assertEquals(userAccessor.retrieveAllUsers().size(), 2);
    }
}
