package com.mycompany.brightmind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton utility class for getting a MySQL database connection.
 */
public class DBUtil {
    private DBUtil() {}

    public static Connection getConnection() {
        try {
            String user = "root";
            String pass = "codse242f-042";
            String url = "jdbc:mysql://localhost:3306/ead";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
