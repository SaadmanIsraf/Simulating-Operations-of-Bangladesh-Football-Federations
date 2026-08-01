package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class player_medical_history_controller
{
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TableColumn player_name_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn date_column;
    @javafx.fxml.FXML
    private TableColumn treatment_plans_column;
    @javafx.fxml.FXML
    private TableColumn injury_report_column;
    @javafx.fxml.FXML
    private TableView player_mdecial_history_tableview;
    @javafx.fxml.FXML
    private TableColumn matchday_incident_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void search_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }
}