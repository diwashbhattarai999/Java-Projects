package com.javaauth.dao;

import com.javaauth.model.User;

import java.sql.*;

public class UserDAO {
    public static Connection getConnection() throws SQLException {
    	 return DBConnection.getConnection();
    }

    public boolean registerUser(User user) {
        String query = "INSERT INTO user (firstName, lastName, email, phoneNumber, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getPassword());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean validateUser(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("User found in database.");
                return true; // If a matching user is found
            } else {
                System.out.println("No user found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isEmailAlreadyRegistered(String email) {
        String query = "SELECT * FROM user WHERE email = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // If email is found, it's already registered
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
