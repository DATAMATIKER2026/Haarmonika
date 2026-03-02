package org.example.monika.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.service.BookingService;
import org.example.monika.service.CustomerService;

public class BookingController {
    @FXML
    private ComboBox<Coworker> coworkerComboBox;
    @FXML
    private ComboBox<Customer> customerComboBox;
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
    private void onReturn(){
        SceneNavigator.switchTo("Oversigt");
    }

    private void loadCoworkers(){
        coworkersItems.setAll(bookingService.getAllCoworkers());
    }

    private void loadCustomers(){
        customerItems.setAll(bookingService.getAllCustomers());
    }

}
