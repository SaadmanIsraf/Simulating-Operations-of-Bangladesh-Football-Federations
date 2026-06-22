package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Dashboard_Controller {
    @javafx.fxml.FXML
    private Button Logout;

    @javafx.fxml.FXML
    public void logout_ON_Action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Login.fxmls");
    }
}
