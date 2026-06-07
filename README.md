[companymanager_README.md](https://github.com/user-attachments/files/28686241/companymanager_README.md)
# Company Manager — Employee Management System

A JavaFX desktop application for managing employee records with role-based authentication and a MySQL backend.

---

## Features

- **Login system** — authenticates users via email and SSN against a live MySQL database
- **Role-based access control** — admin and employee roles with completely separate dashboards
- **Admin panel** — full CRUD: search employees by ID, add new employees, update records (name, email, SSN, salary), and delete employees
- **Employee dashboard** — employees can log in and view their own profile data
- **Secure queries** — all database queries use JDBC prepared statements to prevent SQL injection

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI Framework | JavaFX + FXML |
| Database | MySQL |
| DB Connector | JDBC (mysql-connector-j 9.1.0) |
| Build Tool | Maven |
| Architecture | MVC (Model-View-Controller) |

---

## Project Structure

```
src/main/
├── controller/
│   ├── LoginController.java      # Handles login form and auth flow
│   ├── AdminController.java      # Admin CRUD operations
│   └── EmployeeController.java   # Employee dashboard view
├── model/
│   └── User.java                 # Employee data model
├── db/
│   └── DBConnection.java         # MySQL connection setup
├── util/
│   ├── Auth.java                 # Authentication logic
│   └── AuthResult.java           # Auth result with role + empId
resources/
├── login.fxml                    # Login screen UI
├── admin.fxml                    # Admin panel UI
└── employee.fxml                 # Employee dashboard UI
```

---

## Setup & Running

### Prerequisites
- Java 17+
- Maven
- MySQL running locally

### Database Setup
1. Create a MySQL database called `employeeData`
2. Create an `employees` table:
```sql
CREATE TABLE employees (
    empid INT PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    SSN VARCHAR(20),
    salary DOUBLE
);
```
3. Insert at least one admin user (empid = 1 is treated as admin)

### Configure Connection
Update `DBConnection.java` with your MySQL credentials:
```java
private static final String URL = "jdbc:mysql://localhost:3306/employeeData";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```
> ⚠️ Do not commit real credentials to version control. Use environment variables in production.

### Build & Run
```bash
mvn compile
mvn javafx:run
```

---

## How It Works

1. User enters email and SSN on the login screen
2. App queries the database — if credentials match, an `AuthResult` is returned with the user's role
3. `empid == 1` → routed to the **Admin Panel**; all others → routed to the **Employee Dashboard**
4. Admin can search any employee by ID and perform full CRUD operations
5. Employees see a read-only view of their own data

---

## Author

**Meissen Hsu** — [github.com/frastydragon](https://github.com/frastydragon)
