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

    public void initialize() {
        // Assuming empid stored from login (implement session if needed)
        String empid = "2"; // placeholder
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE empid = ?");
            stmt.setString(1, empid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dataArea.setText("Welcome " + rs.getString("Fname") +
                                 "\nEmail: " + rs.getString("email") +
                                 "\nSalary: $" + rs.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}