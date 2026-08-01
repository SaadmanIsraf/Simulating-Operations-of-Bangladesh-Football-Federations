package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;

public class medical_officer_dashboard_controller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void injury_report_and_fitness_status_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/injury_report_and_fitness_status.fxml");
    }

    @javafx.fxml.FXML
    public void player_medical_history_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/player_medical_history.fxml");
    }

    @javafx.fxml.FXML
    public void logout_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void player_medically_fit_or_banned_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/player_medically_fit_or_banned.fxml");
    }

    @javafx.fxml.FXML
    public void medical_equipment_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_equipment.fxml");
    }

    @javafx.fxml.FXML
    public void player_rehabilitation_progress_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/player_rehabilitation_progress.fxml");
    }

    @javafx.fxml.FXML
    public void medical_incident_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_incident.fxml");
    }

    @javafx.fxml.FXML
    public void treatment_plan_button_on_Action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/treatment_plan.fxml");
    }

    @javafx.fxml.FXML
    public void medical_officer_profile_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_profile.fxml");
    }
}