package com.javaauth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/javaauth";
    private static final String USER = "root";
    private static final String PASSWORD = "Diwash@4477";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connection failed.", e);
        }
    }
}
