package view;

import javax.swing.*;
import controller.MainController;
import model.Patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPatientForm extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JTextField nameField, ageField, contactField;
    private JRadioButton maleButton, femaleButton;
    private JButton saveButton, cancelButton;
    private Patient patient;

    public EditPatientForm(JFrame parent, int patientId, JTable patientTable) {
        super(parent, "Edit Patient", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        // Fetch the patient data to edit
        MainController controller = new MainController();
        patient = controller.getPatientById(patientId);

        // Set up form fields with patient data
        nameField = new JTextField(patient.getName());
        ageField = new JTextField(String.valueOf(patient.getAge()));
        contactField = new JTextField(patient.getContact());
        
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        if (patient.getGender().equals("Male")) {
            maleButton.setSelected(true);
        } else {
            femaleButton.setSelected(true);
        }
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Buttons for saving or canceling
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        // Layout for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        panel.add(genderPanel);
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);

        // Save button logic
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String contact = contactField.getText();
                String gender = maleButton.isSelected() ? "Male" : "Female";

                patient.setName(name);
                patient.setAge(age);
                patient.setContact(contact);
                patient.setGender(gender);

                // Call controller to update patient
                controller.updatePatient(patient);
                JOptionPane.showMessageDialog(EditPatientForm.this, "Patient updated successfully!");

                // Refresh the table in ViewPatientsList after update
                ViewPatientsList.refreshTable();  // Refresh the table in ViewPatientsList

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
