package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController
{
    @javafx.fxml.FXML
    private TextField usernameTF;
    @javafx.fxml.FXML
    private Label messageLabel;
    @FXML
    private PasswordField passwordTF;
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> dashboards = new HashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        // Username -> Password
        users.put("user1", "0000");
        users.put("user2", "1111");
        users.put("user3", "2222");
        users.put("user4", "3333");
        users.put("user5", "4444");
        users.put("user6", "5555");
        users.put("user7", "6666");
        users.put("user8", "7777");
        users.put("user9", "8888");
        users.put("user10", "9999");
        users.put("user11", "911");
        users.put("user12", "1212");

        // Username -> Dashboard

        dashboards.put("user1",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user2",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user3",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Arman/medical_officer/medical_officer_dashboard.fxml");
        dashboards.put("user4",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Arman/player/player_dashboard.fxml");
        dashboards.put("user5",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SpectatorDashboard.fxml");
        dashboards.put("user6",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/TicketManagerDashboard.fxml");
        dashboards.put("user7",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user8",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user9",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user10",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
        dashboards.put("user11",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/dashboardView.fxml");
        dashboards.put("user12",
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/matchofficialsdashboard.fxml");

    }

    @javafx.fxml.FXML
    public void loginButton(ActionEvent actionEvent)throws IOException {
        String username= usernameTF.getText();
        String password= passwordTF.getText();

        //Check User7 username
        if (!users.containsKey(username)) {
            messageLabel.setText("Invalid Username");
            return;
        }
        //Check user7 password
        if (!users.get(username).equals(password)) {
            messageLabel.setText("Invalid Password");
            return;
        }
        // Login successful
        messageLabel.setText("");

        Parent root = FXMLLoader.load(
                getClass().getResource(dashboards.get(username))
        );

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}