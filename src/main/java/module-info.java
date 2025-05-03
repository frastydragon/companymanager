module employeedata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens employeedata.controller to javafx.fxml;
    exports employeedata;
}
