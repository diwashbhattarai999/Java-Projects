package dao;

import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public static Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (name, specialization, contact, availability) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialization());
            statement.setString(3, doctor.getContact());
            statement.setString(4, doctor.getAvailability());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                doctors.add(new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("specialization"),
                    resultSet.getString("contact"),
                    resultSet.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    // Update Doctor
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, specialization = ?, contact = ?, availability = ? WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialization());
            statement.setString(3, doctor.getContact());
            statement.setString(4, doctor.getAvailability());
            statement.setInt(5, doctor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Doctor
    public void deleteDoctor(int doctorId) {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
