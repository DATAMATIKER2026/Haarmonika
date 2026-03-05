package org.example.monika.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.service.LoginService;
import javafx.scene.control.TextField;

public class LoginController {
    // FXML til text fields
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final LoginService loginService = new LoginService();

    //Loginclick validere userlogin og modtager sender user videre via UserSession så vi kan genbruge navnet i frontpage
    @FXML
    private void onLoginClick(){
        try {
            Coworker user = loginService.login(
                    usernameField.getText().trim(),
                    passwordField.getText().trim()
            );
            UserSession.set(user);

            SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
            SceneNavigator.switchTo("Oversigt");

        } catch (Exception ex) {
            showAlert("Fejl", ex.getMessage());
        }
    }

    // ShowAlert viser en fejl besked direkte til brugeren
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
