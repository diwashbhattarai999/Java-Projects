package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.MainController;

public class AddPatientForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtName, txtAge, txtContact;
    private JRadioButton rbMale, rbFemale;
    private ButtonGroup genderGroup;
    private MainController controller;

    public AddPatientForm() {
        setTitle("Add Patient");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new MainController();

        // Create form components
        JLabel lblName = new JLabel("Name:");
        JLabel lblAge = new JLabel("Age:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblContact = new JLabel("Contact:");

        txtName = new JTextField(20);
        txtAge = new JTextField(20);
        txtContact = new JTextField(20);

        // Gender radio buttons
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JButton btnSave = new JButton("Save");

        // Customizing labels and inputs for modern minimalistic design
        lblName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAge.setFont(new Font("Arial", Font.PLAIN, 14));
        lblGender.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContact.setFont(new Font("Arial", Font.PLAIN, 14));

        txtName.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAge.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContact.setFont(new Font("Arial", Font.PLAIN, 16));
        txtName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtAge.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtContact.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        btnSave.setBackground(Color.decode("#3498db")); // Blue color for the button
        btnSave.setForeground(Color.WHITE); // White text
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new Dimension(100, 40));
        btnSave.setBorder(BorderFactory.createEmptyBorder());
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        
        // Apply hover effect
        setButtonHoverEffect(btnSave);

        // Panel for the form, with padding and centered content
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Adding components to the panel
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblAge);
        panel.add(txtAge);
        panel.add(lblGender);
        JPanel genderPanel = new JPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        panel.add(genderPanel);
        panel.add(lblContact);
        panel.add(txtContact);
        panel.add(new JLabel());  // Empty label for layout
        panel.add(btnSave);

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set Window decorations to make the frame have close, minimize, and maximize buttons
        setResizable(true);  // Allow resizing the window
        setVisible(true);

        // Action listener for Save button
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String gender = rbMale.isSelected() ? "Male" : "Female";  // Get selected gender
                String contact = txtContact.getText();

                // Use the controller to save the patient
                controller.addPatient(name, age, gender, contact);

                // Show a success message and reset form
                JOptionPane.showMessageDialog(AddPatientForm.this, "Patient added successfully!");
                txtName.setText("");
                txtAge.setText("");
                genderGroup.clearSelection();
                txtContact.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPatientForm().setVisible(true));
    }

    // Helper method for adding smooth button hover effects
    private void setButtonHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#2980b9")); // Darker blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#3498db")); // Original blue color
            }
        });
    }
}
