module employeedata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens employeedata to javafx.fxml;
    exports employeedata;
}
