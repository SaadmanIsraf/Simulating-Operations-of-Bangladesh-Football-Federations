package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_Controller {
    @javafx.fxml.FXML
    private PasswordField passwordfield;
    @javafx.fxml.FXML
    private TextField textfield;
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private Button login;

    public static String LoggedInUser= "";

    @javafx.fxml.FXML
    public void Login(ActionEvent actionEvent) {

        if (true) {
            label.setText("Login Successful!");
            LoggedInUser = textfield.getText();
            SceneSwitcher.switchTo("Dashboard.fxml");
        }
        else {
            label.setText("login failed!");
        }
    }
}