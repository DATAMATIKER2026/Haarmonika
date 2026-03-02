package org.example.monika.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class SceneNavigator {
    private static Stage mainStage;
    private static HashMap<String, Scene> scenes = new HashMap<>();

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void loadScene(String sceneName, String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scenes.put(sceneName, scene);
    }

    public static String switchTo(String sceneName) {
        Scene scene = scenes.get(sceneName);
        if (scene != null) {
            mainStage.setScene(scene);
            mainStage.show();
        } else {
            String msg = "Scene not found";
            return msg;
        }
        return null;
    }
}
