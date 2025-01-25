package dao;

import java.sql.*;

public class AdminDAO {
    public static Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    public boolean validateAdmin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // If a matching record exists, the login is valid
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // If no matching record exists
    }
}
