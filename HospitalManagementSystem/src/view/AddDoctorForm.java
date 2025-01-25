package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.MainController;

public class AddDoctorForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtName, txtSpecialization, txtContact;
    private JComboBox<String> cmbAvailability;
    private MainController controller;

    public AddDoctorForm() {
        setTitle("Add Doctor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new MainController();

        // Create form components
        JLabel lblName = new JLabel("Name:");
        JLabel lblSpecialization = new JLabel("Specialization:");
        JLabel lblContact = new JLabel("Contact:");
        JLabel lblAvailability = new JLabel("Availability:");

        txtName = new JTextField(20);
        txtSpecialization = new JTextField(20);
        txtContact = new JTextField(20);

        // Availability combo box
        String[] availabilityOptions = {"Available", "Not Available"};
        cmbAvailability = new JComboBox<>(availabilityOptions);

        JButton btnSave = new JButton("Save");

        // Customizing labels and inputs for modern minimalistic design
        lblName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSpecialization.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContact.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAvailability.setFont(new Font("Arial", Font.PLAIN, 14));

        txtName.setFont(new Font("Arial", Font.PLAIN, 16));
        txtSpecialization.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContact.setFont(new Font("Arial", Font.PLAIN, 16));
        txtName.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtSpecialization.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
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
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Adding components to the panel
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblSpecialization);
        panel.add(txtSpecialization);
        panel.add(lblContact);
        panel.add(txtContact);
        panel.add(lblAvailability);
        panel.add(cmbAvailability);
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
                String specialization = txtSpecialization.getText();
                String contact = txtContact.getText();
                String availability = (String) cmbAvailability.getSelectedItem();  // Get selected availability

                // Use the controller to save the doctor
                controller.addDoctor(name, specialization, contact, availability);

                // Show a success message and reset form
                JOptionPane.showMessageDialog(AddDoctorForm.this, "Doctor added successfully!");
                txtName.setText("");
                txtSpecialization.setText("");
                txtContact.setText("");
                cmbAvailability.setSelectedIndex(0);  // Reset combo box selection
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddDoctorForm().setVisible(true));
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
