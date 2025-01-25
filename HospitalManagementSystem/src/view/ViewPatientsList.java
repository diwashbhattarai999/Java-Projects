package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.MainController;
import model.Patient;

public class ViewPatientsList extends JFrame {
    private static final long serialVersionUID = 1L;
    private static DefaultTableModel tableModel;  // To refresh the table after deletion or editing

    public ViewPatientsList() {
        setTitle("Patients List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for displaying patient data
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Use MainController to get list of patients
        MainController controller = new MainController();
        List<Patient> patients = controller.getAllPatients();  // Get all patients through controller

        // Create a table model with the data for patients
        String[] columnNames = {"ID", "Name", "Age", "Gender", "Contact", "Edit", "Delete"};
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Patient patient : patients) {
            Object[] row = new Object[]{
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getContact(),
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
                if (column == 5 || column == 6) { // Edit and Delete columns
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
                    int patientId = (int) table.getValueAt(row, 0);

                    if (label.equals("Edit")) {
                        // Open the Edit form
                        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(table);
                        new EditPatientForm(parentFrame, patientId, table).setVisible(true);
                    } else if (label.equals("Delete")) {
                        // Handle delete action
                        deletePatient(row); // Pass the row index to delete the row
                    }
                }
            });
            this.label = actionType;  // Set the action type (Edit or Delete)
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

        // Method to delete the patient
        private void deletePatient(int row) {
            MainController controller = new MainController();
            int patientId = (int) tableModel.getValueAt(row, 0);  // Get patient ID
            controller.deletePatient(patientId);

            // Remove row from table and refresh UI
            tableModel.removeRow(row);
            System.out.println("Patient with ID " + patientId + " deleted");
        }
    }

    // This method should be called when patient is edited
    public static void refreshTable() {
        MainController controller = new MainController();
        List<Patient> patients = controller.getAllPatients();  // Get all patients again

        // Clear the existing rows
        tableModel.setRowCount(0);

        // Add updated patient data
        for (Patient patient : patients) {
            Object[] row = new Object[]{
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getContact(),
                "Edit",  // Edit button column
                "Delete" // Delete button column
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewPatientsList::new);
    }
}
