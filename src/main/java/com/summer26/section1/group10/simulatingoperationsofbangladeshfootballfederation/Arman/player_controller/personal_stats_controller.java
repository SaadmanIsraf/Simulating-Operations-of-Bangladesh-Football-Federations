package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class personal_stats_controller
{
    @javafx.fxml.FXML
    private TableColumn matches_played_column;
    @javafx.fxml.FXML
    private Label player_information_label;
    @javafx.fxml.FXML
    private TableColumn medical_condition_column;
    @javafx.fxml.FXML
    private Label information_label;
    @javafx.fxml.FXML
    private TableView personal_stats_table;
    @javafx.fxml.FXML
    private TableColumn assists_column;
    @javafx.fxml.FXML
    private TableColumn goals_column;

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