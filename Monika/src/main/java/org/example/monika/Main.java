package org.example.monika;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.monika.Navigation.SceneNavigator;

public class Main extends Application {

    //Set og loading af scener fra start
    @Override
    public void start(Stage stage) throws Exception {
        SceneNavigator.setStage(stage);
        SceneNavigator.loadScene("Login","/loginPage.fxml");
        SceneNavigator.switchTo("Login");
        stage.setTitle("Login");
        stage.show();
    }

    //Launcher
    public static void main(String[] args) {
        launch(args);
    }
}
