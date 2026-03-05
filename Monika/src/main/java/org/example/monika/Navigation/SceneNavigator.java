package org.example.monika.Navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.monika.Infastructure.DbConfig;
import org.example.monika.Repository.BookingRepository;
import org.example.monika.Repository.CoworkerRepository;
import org.example.monika.Repository.CustomerRepository;
import org.example.monika.service.BookingService;
import org.example.monika.service.CustomerService;
import org.example.monika.ui.BookingController;
import org.example.monika.ui.CustomerController;
import org.example.monika.ui.LoginController;

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
   
        fxmlLoader.setControllerFactory(type -> {
            if (type == BookingController.class) {
                DbConfig db = new DbConfig();

                CoworkerRepository coworkerRepository = new CoworkerRepository(db);
                CustomerRepository customerRepository = new CustomerRepository(db);
                BookingRepository bookingRepository = new BookingRepository(db);

                BookingService bookingService = new BookingService(coworkerRepository, customerRepository, bookingRepository);
                CustomerService customerService = new CustomerService(customerRepository);

                return new BookingController(bookingService, customerService);
            }
            if (type == LoginController.class) {
                return new LoginController();
            }
            if (type == CustomerController.class) {
                return new CustomerController(
                        new CustomerService(
                                new CustomerRepository(new DbConfig())
                        )
                );
            }
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scenes.put(sceneName, scene);
    }

    public static void switchTo(String sceneName) {
        mainStage.setScene(scenes.get(sceneName));
    }
}

