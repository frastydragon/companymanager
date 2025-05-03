package employeedata.model;

public class User {
    private String empid;
    private String email;
    private String fname;
    private String lname;
    private double salary;

    public User(String empid, String email, String fname, String lname, double salary) {
        this.empid = empid;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }

    public String getEmpid() { return empid; }
    public String getEmail() { return email; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return fname + " " + lname + " (" + empid + ")";
    }
}
