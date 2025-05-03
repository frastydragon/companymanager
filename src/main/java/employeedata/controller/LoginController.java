package employeedata.controller;


import java.io.IOException;

import employeedata.util.Auth;
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
        String role = Auth.authenticate(emailField.getText(), ssnField.getText());
        if (role != null) {
            try {
                FXMLLoader loader;
                if (role.equals("admin")) {
                    loader = new FXMLLoader(getClass().getResource("/view/admin.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/view/employee.fxml"));
                }
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
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
