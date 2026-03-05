package org.example.monika.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.monika.Model.Customer;
import org.example.monika.Navigation.SceneNavigator;
import org.example.monika.service.CustomerService;

public class CustomerController {

    private final CustomerService customerService;

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerMailField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextField customerAddressField;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    public void onCreateButtonClick() {
        try {
            String fname = customerNameField.getText().trim();
            String mail = customerMailField.getText().trim();
            int tlfnr = Integer.parseInt(customerPhoneField.getText().trim());
            String address = customerAddressField.getText().trim();
            if (fname.isBlank() || mail.isBlank() || customerPhoneField.getText().isBlank()) {
                showAlert("Fejl", "Udfyld venligst alle felter");
                return;
            }
            if (!customerPhoneField.getText().matches("\\d+")) {
                showAlert("Fejl", "Telefonnummer må kun indeholde tal");
                return;
            }

            Customer customer = new Customer(fname, mail, tlfnr, address);
            Customer created = customerService.create(customer);

            showAlert("Oprettet", "Kunde oprettet med ID : " + created.getCustomerId());

        } catch (IllegalArgumentException e) {
            showAlert("Fejl", "Fejl i input");
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
        }
    }

    public void onReturn() {
        try {
            SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
            SceneNavigator.switchTo("Oversigt");
        } catch (Exception e) {
            showAlert("Fejl", "Kritisk fejl, kontakt administrator");
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
