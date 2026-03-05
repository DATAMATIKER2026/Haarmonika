package org.example.monika.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.service.BookingService;
import org.example.monika.service.CustomerService;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {
    @FXML private ComboBox<Coworker> coworkerComboBox;
    @FXML private ComboBox<Customer> customerComboBox;
    @FXML private TextField startTimeField;
    @FXML private TextField durationField;
    @FXML private DatePicker dateChoice;
    @FXML private TextField comment;

    private final BookingService bookingService;
    private final CustomerService customerService;

    private final ObservableList<Coworker> coworkersItems = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerItems = FXCollections.observableArrayList();

    public BookingController(BookingService bookingService, CustomerService customerService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @FXML
    private void initialize() {
        coworkerComboBox.setItems(coworkersItems);
        customerComboBox.setItems(customerItems);
        loadCoworkers();
        loadCustomers();
    }

    @FXML
    private void onCreateBooking(){
        Customer customer = customerComboBox.getValue();
        Coworker coworker = coworkerComboBox.getValue();
        LocalDate date = dateChoice.getValue();

        LocalTime start = LocalTime.parse(startTimeField.getText());
        int duration = Integer.parseInt(durationField.getText());

        boolean available = bookingService.isCoworkerAvailable(coworker.getCoworkerId(), date, start, duration);

        try {
            if (!available) {
                showAlert("Optaget", "Tiden er allerede optaget");
            } else {
                bookingService.createBooking(customer.getCustomerId(), coworker.getCoworkerId(), date, start, duration, comment.getText());
                showAlert("Oprettet", "Booking oprettet med ID " + customer.getCustomerId());
            }
        } catch (IllegalArgumentException e) {
            showAlert("ATTENTION!", e.getMessage());
        }
        //Skal vise error besked hvis frisøren er optaget.
    }

    @FXML
    private void onReturn(){
        SceneNavigator.switchTo("Oversigt");
    }

    private void loadCoworkers(){
        coworkersItems.setAll(bookingService.getAllCoworkers());
    }

    private void loadCustomers(){
        customerItems.setAll(bookingService.getAllCustomers());
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
