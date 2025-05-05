package employeedata.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employeedata.db.DBConnection;

public class Auth {
    public static AuthResult authenticate(String email, String ssn) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT empid FROM employees WHERE email = ? AND SSN = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int empid = rs.getInt("empid");
                String role = empid == 1 ? "admin" : "employee";
                return new AuthResult(role, empid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
