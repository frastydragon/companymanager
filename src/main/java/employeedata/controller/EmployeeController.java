package employeedata.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employeedata.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class EmployeeController {
    @FXML private TextArea dataArea;

    private int empId = -1; // Will be set from LoginController

    // Called by LoginController after loading this controller
    public void setEmployeeId(int empId) {
        this.empId = empId;
        loadEmployeeData();
    }

    // Load employee data based on empId
    private void loadEmployeeData() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE empid = ?");
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dataArea.setText("Welcome " + rs.getString("Fname") +
                                 "\nEmail: " + rs.getString("email") +
                                 "\nSalary: $" + rs.getDouble("Salary"));
            } else {
                dataArea.setText("Employee data not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            dataArea.setText("Error loading data.");
        }
    }
}
