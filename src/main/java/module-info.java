module employeedata {
    requires javafx.controls;
    requires javafx.fxml;

    opens employeedata to javafx.fxml;
    exports employeedata;
}
