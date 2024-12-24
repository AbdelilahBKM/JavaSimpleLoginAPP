package com.example.javafxproject0.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label welcomeLabel;
    @FXML
    public void initialize(){
        welcomeLabel.setText("Welcome to the Home Page");
    }
}
