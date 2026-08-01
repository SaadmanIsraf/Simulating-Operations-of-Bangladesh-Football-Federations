package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class team_details_controller
{
    @javafx.fxml.FXML
    private Label team_name_label;
    @javafx.fxml.FXML
    private Label captain_name_label;
    @javafx.fxml.FXML
    private Label information_label;
    @javafx.fxml.FXML
    private ListView team_members_listview;
    @javafx.fxml.FXML
    private Label coach_name_label;
    @javafx.fxml.FXML
    private TextArea team_description_textarea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refresh_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}