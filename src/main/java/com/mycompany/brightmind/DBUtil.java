/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brightmind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Poorna
 */
public class DBUtil {
    private static Connection connection;

    private DBUtil() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/your_db";
                String user = "root";
                String pass = "your_password";
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
