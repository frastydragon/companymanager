package employeedata.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employeedata.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML private TextField empidSearchField;
    @FXML private TextArea resultArea;

    @FXML
    private void handleSearch() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE empid = ?");
            stmt.setString(1, empidSearchField.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultArea.setText("Employee: " + rs.getString("Fname") + " " + rs.getString("Lname") +
                                   "\nEmail: " + rs.getString("email") +
                                   "\nSalary: $" + rs.getDouble("Salary"));
            } else {
                resultArea.setText("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}