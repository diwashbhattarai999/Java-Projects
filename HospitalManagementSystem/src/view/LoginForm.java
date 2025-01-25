package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.MainController;

public class LoginForm extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private MainController controller;

    public LoginForm() {
        setTitle("Admin Login - Hospital Management System");
        setSize(600, 400);  // Increased window size to 600x400
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new MainController();

        // Create form components
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        
        // Add "Hospital Management System" title at the top
        JLabel lblSystemTitle = new JLabel("Hospital Management System", SwingConstants.CENTER);
        lblSystemTitle.setFont(new Font("Arial", Font.BOLD, 28));  // Larger font size for system title
        lblSystemTitle.setForeground(Color.decode("#3498db"));  // Blue color for the system title
        
        // Add Admin Login Form title
        JLabel lblTitle = new JLabel("Admin Login Form", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));  // Larger font size for the title
        lblTitle.setForeground(Color.decode("#3498db"));  // Blue color for the title

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");

        // Customizing labels and inputs for modern minimalistic design
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));

        txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(Color.decode("#3498db")); // Blue color for the button
        btnLogin.setForeground(Color.WHITE); // White text
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(100, 40));
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Pointer cursor on hover
        
        // Apply hover effect
        setButtonHoverEffect(btnLogin); 
        
        // Panel for the form, with padding and centered content
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));  // Adjusted for extra title labels
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Adding components to the panel
        panel.add(lblTitle);  // Add admin login form title
        panel.add(new JLabel());  // Empty label for layout
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel());  // Empty label for layout
        panel.add(btnLogin);
        
        // Add system title at the top
        add(lblSystemTitle, BorderLayout.NORTH);
        
        // Add panel to the center of the frame
        add(panel, BorderLayout.CENTER);

        // Set Window decorations to make the frame have close, minimize, and maximize buttons
        setResizable(true);  // Allow resizing the window
        setVisible(true);
        
        // Action listener for Login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (controller.checkAdminCredentials(username, password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login Successful!");
                    // Open the admin dashboard after successful login
                    new AdminDashboard().setVisible(true);
                    dispose(); // Close the login form
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid Username or Password");
                }
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
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
