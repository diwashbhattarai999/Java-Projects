package dao;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public static Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    // Method to add a new patient
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (name, age, gender, contact) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getContact());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the all patients
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                patients.add(new Patient(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("contact")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    // Method to update the patient's details
    public void updatePatient(Patient patient) {
        String query = "UPDATE patients SET name = ?, age = ?, gender = ?, contact = ? WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getContact());
            statement.setInt(5, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a patient by ID
    public void deletePatient(int patientId) {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get patient by id
    public Patient getPatientById(int patientId)  {
    	Patient patient = null; 
        String query = "SELECT * FROM patients WHERE id = ?"; 
        
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            
            // Check if a result is returned
            if (resultSet.next()) {
                // Create a new Patient object with the fetched details
                patient = new Patient(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("contact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patient;
    }
}
