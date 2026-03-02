package org.example.monika.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import  org.example.monika.Infastructure.DbConfig;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.Repository.CustomerRepository;

public class FrontPageController {
    @FXML private TextField customerSearch;
    @FXML private Button searchButton;
    @FXML private TextField userField;

    @FXML private ListView<String> f1;
    @FXML private ListView<String> f2;
    @FXML private ListView<String> f3;
    @FXML private ListView<String> f4;

    @FXML private Button newBooking;
    @FXML private Button newCostumer;

    @FXML private TextField coworkerOne;
    @FXML private TextField coworkerTwo;
    @FXML private TextField coworkerThree;
    @FXML private TextField coworkerFour;

    @FXML private DatePicker calender;

    private ObservableList<String> list1 = FXCollections.observableArrayList();
    private ObservableList<String> list2 = FXCollections.observableArrayList();
    private ObservableList<String> list3 = FXCollections.observableArrayList();
    private ObservableList<String> list4 = FXCollections.observableArrayList();

    private CustomerRepository customerRepo;


    @FXML
    private void initialize() {
        Coworker user = UserSession.get();
        System.out.println(user.getFname());
        if (user != null) {
            userField.setText("Velkommen, " + user.getFname());
        }
        this.customerRepo = new CustomerRepository(new DbConfig());

        f1.setItems(list1);
        f2.setItems(list2);
        f3.setItems(list3);
        f4.setItems(list4);
    }

    @FXML
    private void onClickNewCustomer() throws IOException {
        SceneNavigator.loadScene("KundeOprettelse", "\"/customerPage.fxml\"");
        SceneNavigator.switchTo("KundeOprettelse");
    }

    @FXML
    private void onClickNewBooking() throws IOException {
        SceneNavigator.loadScene("Booking", "/bookingPage.fxml");
        SceneNavigator.switchTo("Booking");
    }

    @FXML
    private void searchCustomer() {
        String searchTerm  = customerSearch.getText();

        if (searchTerm.isEmpty()) {
            return;
        }

        Customer foundCustomer = customerRepo.findByName(searchTerm);

        if (foundCustomer != null) {
            String displayText = foundCustomer.getFname();
            int coworkerId = foundCustomer.getCoworkerId();

            switch (coworkerId) {
                case 1:
                    list1.add(displayText);
                    break;
                case 2:
                    list2.add(displayText);
                    break;
                case 3:
                    list3.add(displayText);
                    break;
                case 4:
                    list4.add(displayText);
                    break;
                default:
                    showAlert ("Ingen arbejder", "Kunden er ikke tildelt en medarbejder.");
                    break;
            }
        } else {
            showAlert("Ikke fundet", "Der er ikke blevet fundet en kunde med navnet: " + searchTerm);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}