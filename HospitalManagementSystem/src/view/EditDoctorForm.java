package view;

import javax.swing.*;
import controller.MainController;
import model.Doctor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDoctorForm extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JTextField nameField, specializationField, contactField, availabilityField;
    private JButton saveButton, cancelButton;
    private Doctor doctor;

    public EditDoctorForm(JFrame parent, int doctorId, JTable doctorTable) {
        super(parent, "Edit Doctor", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        // Fetch the doctor data to edit
        MainController controller = new MainController();
        doctor = controller.getDoctorById(doctorId);

        // Set up form fields with doctor data
        nameField = new JTextField(doctor.getName());
        specializationField = new JTextField(doctor.getSpecialization());
        contactField = new JTextField(doctor.getContact());
        availabilityField = new JTextField(doctor.getAvailability());

        // Buttons for saving or canceling
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        // Layout for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(new JLabel("Availability:"));
        panel.add(availabilityField);
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);

        // Save button logic
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String specialization = specializationField.getText();
                String contact = contactField.getText();
                String availability = availabilityField.getText();

                doctor.setName(name);
                doctor.setSpecialization(specialization);
                doctor.setContact(contact);
                doctor.setAvailability(availability);

                // Call controller to update doctor
                controller.updateDoctor(doctor);
                JOptionPane.showMessageDialog(EditDoctorForm.this, "Doctor updated successfully!");

                // Refresh the table in ViewDoctorsList after update
                ViewDoctorsList.refreshTable();  // Refresh the table in ViewDoctorsList

                dispose();  // Close the form
            }
        });

        // Cancel button logic
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the form without saving
            }
        });
    }
}
