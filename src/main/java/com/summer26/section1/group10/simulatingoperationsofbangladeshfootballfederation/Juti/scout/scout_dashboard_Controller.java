package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.scout;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;

public class scout_dashboard_Controller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void validateReportButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/validate_report.fxml");
    }

    @javafx.fxml.FXML
    public void updateReportButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/update_report.fxml");
    }

    @javafx.fxml.FXML
    public void recordPerformanceButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/record_player_performance.fxml");
    }

    @javafx.fxml.FXML
    public void searchPlayersButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/search_player.fxml");
    }

    @javafx.fxml.FXML
    public void generatSummaryButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/creating_scout_report.fxml");
    }

    @javafx.fxml.FXML
    public void createReportButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/create_scouting_report.fxml");
    }

    @javafx.fxml.FXML
    public void viewAssignedMatchesButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/view_assignMatch.fxml");
    }

    @javafx.fxml.FXML
    public void recommenPlayersButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/recommend_player.fxml");
    }

    @javafx.fxml.FXML
    public void myprofileButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/scout/my_profile.fxml");
    }
}