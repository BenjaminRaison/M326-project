package net.baumink.bzz.m326.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Db connection.
 */
public class DBConnection {

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:ucanaccess://data/CoolShoes.accdb");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database!", e);
        }
    }

}
