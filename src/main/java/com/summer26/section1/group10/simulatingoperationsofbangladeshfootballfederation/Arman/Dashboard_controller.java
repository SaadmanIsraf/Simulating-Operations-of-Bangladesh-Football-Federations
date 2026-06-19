package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Dashboard_controller {
    @javafx.fxml.FXML
    private Button logout;
    @javafx.fxml.FXML
    private Label User;

    @javafx.fxml.FXML
    public void Logout(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Login.fxml");
    }
}
