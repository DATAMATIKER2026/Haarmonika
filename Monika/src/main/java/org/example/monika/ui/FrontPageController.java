package org.example.monika.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import  org.example.monika.Infastructure.DbConfig;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.Repository.CustomerRepository;
import org.example.monika.service.FrontPageService;
import org.example.monika.service.BookingService;

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

    private FrontPageService frontPageService;


    @FXML
    private void initialize() {
        Coworker user = UserSession.get();
        if (user != null) {
            userField.setText("Velkommen, " + user.getFname());
        }
        this.frontPageService = new FrontPageService();

        f1.setItems(list1);
        f2.setItems(list2);
        f3.setItems(list3);
        f4.setItems(list4);

        calender.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    frontPageService.loadAllBookings(newValue, list1, list2, list3, list4);
                }
            }

        });
        calender.setValue(LocalDate.now());
    }

    @FXML
    private void onClickNewCustomer() throws IOException {
        SceneNavigator.loadScene("KundeOprettelse", "/customerPage.fxml");
        SceneNavigator.switchTo("KundeOprettelse");
    }

    @FXML
    private void onClickNewBooking() throws IOException {
        SceneNavigator.loadScene("Booking", "/bookingPage.fxml");
        SceneNavigator.switchTo("Booking");
    }

    @FXML
    private void searchCustomer() {
        String searchTerm = customerSearch.getText();

        if (searchTerm.isEmpty()) {
            return;
        }

        frontPageService.searchAndSort(searchTerm, list1, list2, list3, list4);

        if (list1.isEmpty() && list2.isEmpty() && list3.isEmpty() && list4.isEmpty()) {
            showAlert("Ikke fundet", "Der blev ikke fundet en kunde med navnet: " + searchTerm);
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