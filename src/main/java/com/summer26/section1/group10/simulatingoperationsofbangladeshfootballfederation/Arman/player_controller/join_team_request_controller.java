package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class join_team_request_controller
{
    @javafx.fxml.FXML
    private TableView team_table;
    @javafx.fxml.FXML
    private TextField team_search_textfield;
    @javafx.fxml.FXML
    private TableColumn team_name_column;
    @javafx.fxml.FXML
    private TableColumn coach_name_column;
    @javafx.fxml.FXML
    private TableColumn team_status_column;
    @javafx.fxml.FXML
    private Label information_label;
    @javafx.fxml.FXML
    private Button send_request_button;
    @javafx.fxml.FXML
    private Label selected_team_label;
    @javafx.fxml.FXML
    private TableColumn team_id_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void send_request_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void search_team_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}