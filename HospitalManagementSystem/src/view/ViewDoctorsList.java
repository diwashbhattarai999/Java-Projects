package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Doctor;

public class ViewDoctorsList extends JFrame {
    private static final long serialVersionUID = 1L;
    private static DefaultTableModel tableModel;  // To refresh the table after deletion or editing

    public ViewDoctorsList() {
        setTitle("Doctors List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for displaying doctor data
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Use MainController to get list of doctors
        MainController controller = new MainController();
        List<Doctor> doctors = controller.getAllDoctors();  // Get all doctors through controller

        // Create a table model with the data for doctors
        String[] columnNames = {"ID", "Name", "Age", "Gender", "Contact", "Edit", "Delete"};

        tableModel = new DefaultTableModel(columnNames, 0);
        for (Doctor doctor : doctors) {
            Object[] row = new Object[] {
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getContact(),
                doctor.getAvailability(),
                "Edit",  // Edit button column
                "Delete" // Delete button column
            };
            tableModel.addRow(row);
        }

        // Create the JTable with the table model
        JTable table = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5 || column == 6) { // Actions column for Edit and Delete buttons
                    return JButton.class;
                }
                return super.getColumnClass(column);
            }
        };

        // Set action buttons for each row in the Edit and Delete columns
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), "Edit"));

        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    // Custom cell renderer for the "Actions" column
    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        public ButtonRenderer() {
            setFocusPainted(false);
            setContentAreaFilled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((String) value);
            return this;
        }
    }

    // Custom cell editor for the "Actions" column
    private static class ButtonEditor extends DefaultCellEditor {
        private static final long serialVersionUID = 1L;
        
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String actionType) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    JTable table = (JTable) button.getClientProperty("table");
                    int row = table.getSelectedRow();
                    int doctorId = (int) table.getValueAt(row, 0);

                    if (label.equals("Edit")) {
                        // Open the Edit form
                        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(table);
                        new EditDoctorForm(parentFrame, doctorId, table).setVisible(true);
                    } else if (label.equals("Delete")) {
                        // Handle delete action
                        deleteDoctor(row);
                    }
                }
            });
            this.label = actionType;  
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(label);
            button.putClientProperty("table", table);  // Store table reference
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Handle button press logic here
                isPushed = false;
            }
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        // Method to delete the doctor
        private void deleteDoctor(int row) {
            MainController controller = new MainController();
            int doctorId = (int) tableModel.getValueAt(row, 0);  // Get doctor ID
            controller.deleteDoctor(doctorId);

            // Remove row from table and refresh UI
            tableModel.removeRow(row);
            System.out.println("Doctor with ID " + doctorId + " deleted");
        }
    }

    // This method should be called when doctor is edited
    public static void refreshTable() {
        MainController controller = new MainController();
        List<Doctor> doctors = controller.getAllDoctors();  // Get all doctors again

        // Clear the existing rows
        tableModel.setRowCount(0);

        // Add updated doctor data
        for (Doctor doctor : doctors) {
            Object[] row = new Object[]{
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getAvailability(),
                doctor.getContact(),
                "Edit",  // Edit button column
                "Delete" // Delete button column
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewDoctorsList::new);
    }
}

