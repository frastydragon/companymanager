package employeedata.controller;

import java.io.IOException;

import employeedata.util.Auth;
import employeedata.util.AuthResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField ssnField;

    @FXML
    private void handleLogin(ActionEvent event) {
        AuthResult result = Auth.authenticate(emailField.getText(), ssnField.getText());
        if (result != null) {
            try {
                FXMLLoader loader;
                Stage stage = (Stage) emailField.getScene().getWindow();

                if (result.role.equals("admin")) {
                    loader = new FXMLLoader(getClass().getResource("/view/admin.fxml"));
                    stage.setScene(new Scene(loader.load()));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/view/employee.fxml"));
                    Scene scene = new Scene(loader.load());

                    // Pass employee ID to employee controller
                    EmployeeController controller = loader.getController();
                    controller.setEmployeeId(result.empId);

                    stage.setScene(scene);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid login credentials");
            alert.show();
        }
    }
}
