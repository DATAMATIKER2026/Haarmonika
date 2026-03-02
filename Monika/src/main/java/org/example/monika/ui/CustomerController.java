package org.example.monika.ui;

import javafx.fxml.FXML;
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
    @FXML
    private Label errorLabel;

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

            errorLabel.setText("Kunde oprettet med ID : " + created.getCustomerId());

        } catch (IllegalArgumentException e) {
            System.out.println("Fejl i input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Uventet fejl: " + e.getMessage());
        }
    }

    public void onReturn(){
        try {
            SceneNavigator.loadScene("Oversigt", "/frontPage.fxml");
            SceneNavigator.switchTo("Oversigt");
        } catch (Exception e) {
            System.out.println("Kritisk fejl, kontakt administrator");
        }
    }
}
