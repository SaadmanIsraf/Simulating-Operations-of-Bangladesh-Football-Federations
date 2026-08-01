package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class coach_information_controller
{
    @javafx.fxml.FXML
    private Label coach_specialization_label;
    @javafx.fxml.FXML
    private TextArea coach_schedule_textarea;
    @javafx.fxml.FXML
    private Label player_team_label;
    @javafx.fxml.FXML
    private Label information_label;
    @javafx.fxml.FXML
    private Label coach_contact_label;
    @javafx.fxml.FXML
    private Label coach_name_label;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refresh_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void coach_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}