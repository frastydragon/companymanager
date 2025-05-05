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
    @FXML private TextField fnameField;
    @FXML private TextField lnameField;
    @FXML private TextField emailField;
    @FXML private TextField ssnField;
    @FXML private TextField salaryField;
    @FXML private TextArea resultArea;

    // READ
    @FXML
    private void handleSearch() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE empid = ?");
            stmt.setString(1, empidSearchField.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                fnameField.setText(rs.getString("Fname"));
                lnameField.setText(rs.getString("Lname"));
                emailField.setText(rs.getString("email"));
                ssnField.setText(rs.getString("SSN"));
                salaryField.setText(String.valueOf(rs.getDouble("Salary")));
                resultArea.setText("Employee found.");
            } else {
                resultArea.setText("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error during search.");
        }
    }

    // UPDATE
    @FXML
    private void handleUpdate() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE employees SET Fname = ?, Lname = ?, email = ?, SSN = ?, Salary = ? WHERE empid = ?"
            );
            stmt.setString(1, fnameField.getText());
            stmt.setString(2, lnameField.getText());
            stmt.setString(3, emailField.getText());
            stmt.setString(4, ssnField.getText());
            stmt.setDouble(5, Double.parseDouble(salaryField.getText()));
            stmt.setString(6, empidSearchField.getText());

            int rows = stmt.executeUpdate();
            resultArea.setText(rows > 0 ? "Employee updated." : "Update failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error during update.");
        }
    }

    // DELETE
    @FXML
    private void handleDelete() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE empid = ?");
            stmt.setString(1, empidSearchField.getText());

            int rows = stmt.executeUpdate();
            resultArea.setText(rows > 0 ? "Employee deleted." : "Delete failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error during deletion.");
        }
    }

    // CREATE
    @FXML
    private void handleCreate() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO employees (Fname, Lname, email, SSN, Salary) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, fnameField.getText());
            stmt.setString(2, lnameField.getText());
            stmt.setString(3, emailField.getText());
            stmt.setString(4, ssnField.getText());
            stmt.setDouble(5, Double.parseDouble(salaryField.getText()));

            int rows = stmt.executeUpdate();
            resultArea.setText(rows > 0 ? "New employee added." : "Create failed.");
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error during creation.");
        }
    }
}
