package controller;

import dao.PatientDAO;
import dao.DoctorDAO;
import dao.AdminDAO;

import model.Patient;
import model.Doctor;

import java.util.*;

public class MainController {
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AdminDAO adminDAO;
    
    // Static variable to track login state
    private static boolean isLoggedIn = false;

    public MainController() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        adminDAO = new AdminDAO();
    }

    // Add New Patient
    public void addPatient(String name, int age, String gender, String contact) {
        Patient patient = new Patient(0, name, age, gender, contact);
        patientDAO.addPatient(patient);
    }
    
    
    // Edit Patient
    public void updatePatient(Patient patient) {
    	patientDAO.updatePatient(patient);  // Update patient details in the database
    }

   // Delete Patient
   public void deletePatient(int patientId) {
       patientDAO.deletePatient(patientId);  // Delete patient from the database
   }
   
   public List<Patient> getAllPatients() {
	    return patientDAO.getPatients();  // Fetch all patients from the database via DAO
	}

   
   // Get Patient By Id
   public Patient getPatientById(int patientId) {
       return patientDAO.getPatientById(patientId);
   }

   // Add Doctor
   public void addDoctor(String name, String specialization, String contact, String availability) {
       Doctor doctor = new Doctor(0, name, specialization, contact, availability);
       doctorDAO.addDoctor(doctor);
   }

   // Update Doctor
   public void updateDoctor(Doctor doctor) {
       doctorDAO.updateDoctor(doctor);  // Update doctor details in the database
   }

   // Delete Doctor
   public void deleteDoctor(int doctorId) {
       doctorDAO.deleteDoctor(doctorId);  // Delete doctor from the database
   }

   // Get all Doctors
   public List<Doctor> getAllDoctors() {
       return doctorDAO.getDoctors();  // Fetch all doctors from the database via DAO
   }

   // Get Doctor By Id
   public Doctor getDoctorById(int doctorId) {
       return doctorDAO.getDoctors().stream()
               .filter(d -> d.getId() == doctorId)
               .findFirst()
               .orElse(null);
   }
    
    public boolean checkAdminCredentials(String username, String password) {
        // If the credentials are valid, set the login status to true
        if (adminDAO.validateAdmin(username, password)) {
            isLoggedIn = true;  // Set login status to true
            return true;
        }
        return false;  // If the credentials are incorrect, return false
    }
    
    // Method to log out the user (reset the login status)
    public static void logout() {
        isLoggedIn = false;  // Reset login status to false
    }
    
    // Getter method to check if the user is logged in
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
 
}
