package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class player_profile_controller
{
    @javafx.fxml.FXML
    private TextField player_age_textfield;
    @javafx.fxml.FXML
    private PasswordField new_password_field;
    @javafx.fxml.FXML
    private Label team_name_label;
    @javafx.fxml.FXML
    private PasswordField current_password_field;
    @javafx.fxml.FXML
    private Label playing_position_label;
    @javafx.fxml.FXML
    private TextField player_contact_textfield;
    @javafx.fxml.FXML
    private TextField player_name_textfield;
    @javafx.fxml.FXML
    private Label player_id_label;
    @javafx.fxml.FXML
    private Label information_label;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}