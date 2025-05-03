package employeedata.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employeedata.db.DBConnection;

public class Auth {
    public static String authenticate(String email, String ssn) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT empid FROM employees WHERE email = ? AND SSN = ?");
            stmt.setString(1, email);
            stmt.setString(2, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String empid = rs.getString("empid");
                return empid.equals("1") ? "admin" : empid; // Assume empid '1' is admin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}