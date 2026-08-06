package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class player_dashboard_controller {

    @FXML
    public void initialize() {
    }

    @FXML
    public void coach_information_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/coach_information.fxml");
    }

    @FXML
    public void training_schedule_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/training_schedule.fxml");
    }

    @FXML
    public void match_schedule_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_match_schedule.fxml");
    }

    @FXML
    public void team_details_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/team_details.fxml");
    }

    @FXML
    public void logout_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("login.fxml");
    }

    @FXML
    public void join_team_request_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/join_team_request.fxml");
    }

    @FXML
    public void player_profile_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_profile.fxml");
    }

    @FXML
    public void personal_stats_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/personal_stats.fxml");
    }

    @FXML
    public void tournament_rules_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/tournament_rules.fxml");
    }
}