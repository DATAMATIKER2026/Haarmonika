package org.example.monika.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;
import org.example.monika.service.LoginService;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private final LoginService loginService = new LoginService();

    @FXML
    private void onLoginClick(){
        try {
            Coworker user = loginService.login(
                    usernameField.getText(),
                    passwordField.getText()
            );
            UserSession.set(user);

            SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
            SceneNavigator.switchTo("Oversigt");

        } catch (Exception ex) {
            errorLabel.setText(ex.getMessage());
        }
    }
}
