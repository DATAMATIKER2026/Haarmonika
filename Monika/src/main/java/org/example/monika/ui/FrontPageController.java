package org.example.monika.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.monika.Infastructure.DbConfig;

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
    //Vores velkommen label og search field til customer
    @FXML
    private TextField customerSearch;
    @FXML
    private Label userField;

    //Id på den liste vi ønsker at ændre
    @FXML
    private ListView<String> f1;
    @FXML
    private ListView<String> f2;
    @FXML
    private ListView<String> f3;
    @FXML
    private ListView<String> f4;

    //Datepicker til at vælge dato
    @FXML
    private DatePicker calender;

    //Vores ObservableList til at indsætte data i frontpage
    private ObservableList<String> list1 = FXCollections.observableArrayList();
    private ObservableList<String> list2 = FXCollections.observableArrayList();
    private ObservableList<String> list3 = FXCollections.observableArrayList();
    private ObservableList<String> list4 = FXCollections.observableArrayList();

    private FrontPageService frontPageService;

    @FXML
    private void initialize() {
        try {
            Coworker user = UserSession.get();
            if (user != null) {
                userField.setText("Velkommen, " + (user.getFname() == null ? "" : user.getFname()));
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
                        try {
                            frontPageService.loadAllBookings(newValue, list1, list2, list3, list4);
                        } catch (Exception e) {
                            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
                        }
                    }
                }

            });
            calender.setValue(LocalDate.now());
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
        }
    }

    @FXML
    private void onClickNewCustomer() {
        try {
            SceneNavigator.loadScene("KundeOprettelse", "/customerPage.fxml");
            SceneNavigator.switchTo("KundeOprettelse");
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl kontakt administrator");
        }
    }

    @FXML
    private void onClickNewBooking() {
        try {
            SceneNavigator.loadScene("Booking", "/bookingPage.fxml");
            SceneNavigator.switchTo("Booking");
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
        }
    }

    public void onLogOut() {
        try {
            SceneNavigator.loadScene("Login","/loginPage.fxml");
            SceneNavigator.switchTo("Login");
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
        }
    }

    @FXML
    private void searchCustomer() {
        try {
            String searchTerm = customerSearch.getText().trim();

            if (searchTerm.isEmpty()) {
                return;
            }

            frontPageService.searchAndSort(searchTerm, list1, list2, list3, list4);

            if (list1.isEmpty() && list2.isEmpty() && list3.isEmpty() && list4.isEmpty()) {
                showAlert("Ikke fundet", "Der blev ikke fundet en kunde med navnet: " + searchTerm);
            }
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
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