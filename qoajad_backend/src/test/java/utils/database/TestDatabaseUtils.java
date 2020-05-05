package utils.database;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseUtils {

    public static final String DATABASE_NAME = "test";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final int PORT = 3306;

    public static final JdbcTemplate createMockedJdbcTemplate(final String databaseUrl) throws SQLException {
        final Connection mockedConnection = new MockedSQLConnection(DriverManager.getConnection(databaseUrl, USERNAME, PASSWORD));
        final DataSource mockedDataSource = new MockedSQLDataSource(mockedConnection);
        final JdbcTemplate mockedJdbcTemplate = new JdbcTemplate(mockedDataSource);
        return mockedJdbcTemplate;
    }
}
