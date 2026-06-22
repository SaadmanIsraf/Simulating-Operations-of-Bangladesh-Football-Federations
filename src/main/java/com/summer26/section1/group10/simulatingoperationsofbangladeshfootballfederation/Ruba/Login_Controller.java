package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_Controller {

    @javafx.fxml.FXML
    private TextField UserName;
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private Button login;
    @javafx.fxml.FXML
    private PasswordField Password;

    public static String LoggedInUser = "";
    @javafx.fxml.FXML
    public void login_On_Action(ActionEvent actionEvent) {
        if(true) {
        label.setText("Login Successful!");
        LoggedInUser = UserName.getText();
        SceneSwitcher.switchTo("DashBoard.fxml");
    }
    else {
        label.setText("login failed!");
      }
    }
}
