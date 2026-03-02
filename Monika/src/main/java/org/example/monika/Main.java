package org.example.monika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.monika.ui.SceneNavigator;

public class Main extends Application {

    //Set og loading af scener fra start
    @Override
    public void start(Stage stage) throws Exception {
        SceneNavigator.setStage(stage);

        SceneNavigator.loadScene("Login", "/loginPage.fxml");
        // Oversigt udkomenteret så vi kan opdatere i login.
        //SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
        //SceneNavigator.loadScene("Booking", "/bookingPage.fxml");
        SceneNavigator.loadScene("KundeOprettelse", "/customerPage.fxml");

        SceneNavigator.switchTo("Login");

        stage.setTitle("Login");
        stage.show();
    }

    //Launcher
    public static void main(String[] args) {
        launch(args);
    }
}
