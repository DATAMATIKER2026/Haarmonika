package org.example.monika.ui;

import  org.example.monika.Infastructure.DbConfig;

import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.monika.Model.Coworker;

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


    @FXML
    private void initialize() {
        Coworker user = UserSession.get();
        System.out.println(user.getFname());
        if (user != null) {
            userField.setText("Velkommen, " + user.getFname());
        }
    }

}
