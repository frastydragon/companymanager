package employeedata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employeeData"; // Adjust DB name
    private static final String USER = "root";
    private static final String PASSWORD = "meissen30097";//your own password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}