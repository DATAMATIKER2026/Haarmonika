package org.example.monika.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.monika.Model.Coworker;
import org.example.monika.Model.Customer;
import org.example.monika.service.CustomerService;

import java.sql.SQLException;

public class CustomerController {

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerMailField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextField customerAddressField;

    private final CustomerService customerService = new CustomerService();

    public void onCreateButtonClick() {
        try {
            String fname = customerNameField.getText();
            String mail = customerMailField.getText();
            int tlfnr = Integer.parseInt(customerPhoneField.getText());
            String address = customerAddressField.getText();

            Customer customer = new Customer(fname, mail, tlfnr, address);

            Customer created = customerService.create(customer);

            System.out.println("Kunde oprettet med ID : " + created.getCustomerId());

        } catch (IllegalArgumentException e) {
            System.out.println("Fejl i input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Uventet fejl: " + e.getMessage());
        }
    }

    public void onReturn(){
        try {
            SceneNavigator.switchTo("KundeOprettelse");
        } catch (Exception e) {
            System.out.println("Kritisk fejl, kontakt administrator");
        }
    }
}
