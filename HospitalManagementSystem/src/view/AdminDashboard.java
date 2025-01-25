package view;

import javax.swing.*;
import controller.MainController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private static final long serialVersionUID = 1L;

    public AdminDashboard() {
        // Check if the user is logged in, if not, redirect to the login form
        if (!MainController.isLoggedIn()) {
            new LoginForm().setVisible(true);  // Open the login form
            dispose();  // Close the current window
            return;
        }

        // Setting the title and layout for the frame
        setTitle("Admin Dashboard");
        setSize(800, 600);  // Increased window size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame on the screen

        // Create the header panel with a label
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#2c3e50"));  // Dark blue background
        JLabel headerLabel = new JLabel("Admin Dashboard");
        headerLabel.setForeground(Color.WHITE);  // White text color
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(headerLabel);

        // Create the buttons for managing doctors and patients
        JButton btnManageDoctors = createButton("Manage Doctors");
        JButton btnManagePatients = createButton("Manage Patients");
        JButton btnViewDoctors = createButton("View Doctors");
        JButton btnViewPatients = createButton("View Patients");

        // Create a panel for the buttons and set a layout (2 rows, 2 columns)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));  // 2 rows, 2 columns with spacing
        buttonPanel.setBackground(Color.WHITE);  // White background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding around the panel
        buttonPanel.add(btnManageDoctors);
        buttonPanel.add(btnManagePatients);
        buttonPanel.add(btnViewDoctors);
        buttonPanel.add(btnViewPatients);

        // Adding the panels to the frame with BorderLayout
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create buttons with improved UI/UX
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.decode("#2980b9"));  // Blue background for buttons
        button.setForeground(Color.WHITE);  // White text color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#3498db"), 2));  // Light blue border
        button.setPreferredSize(new Dimension(150, 40));  // Adjusted smaller button size

        // Add hover effect for better UX
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#3498db"));  // Lighter blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#2980b9"));  // Back to original blue
            }
        });

        // Action listener for button clicks
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Manage Doctors")) {
                    // Open the Doctor Management form on top of the dashboard
                    new AddDoctorForm().setVisible(true);  
                } else if (text.equals("Manage Patients")) {
                    // Open the Patient Management form on top of the dashboard
                    new AddPatientForm().setVisible(true);
                } else if (text.equals("View Doctors")) {
                    // Open the View Doctors list form
                    new ViewDoctorsList().setVisible(true);  
                } else if (text.equals("View Patients")) {
                    // Open the View Patients list form
                    new ViewPatientsList().setVisible(true);
                }
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}
