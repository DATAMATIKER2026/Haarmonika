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
            String fname = customerNameField.getText();
            String mail = customerMailField.getText();
            int tlfnr = Integer.parseInt(customerPhoneField.getText());
            String address = customerAddressField.getText();

            Customer customer = new Customer(fname, mail, tlfnr, address);
            Customer created = customerService.create(customer);

            showAlert("Oprettet", "Kunde oprettet med ID : " + created.getCustomerId());

        } catch (IllegalArgumentException e) {
            showAlert("Forkert input", "Fejl i input");
        } catch (Exception e) {
            showAlert("KRITISK FEJL", "Kritisk fejl, kontakt administrator");
        }
    }

    public void onReturn(){
        try {
            SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
            SceneNavigator.switchTo("Oversigt");
        } catch (Exception e) {
            showAlert("KRITISK FEJL", "Kritisk fejl, kontakt administrator");
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
