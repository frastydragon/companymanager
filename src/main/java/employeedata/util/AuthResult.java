package employeedata.util;

public class AuthResult {
    public final String role;
    public final int empId;

    public AuthResult(String role, int empId) {
        this.role = role;
        this.empId = empId;
    }
}