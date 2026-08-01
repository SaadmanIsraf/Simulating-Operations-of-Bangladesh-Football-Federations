package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class player_medically_fit_or_banned_controller
{
    @javafx.fxml.FXML
    private DatePicker matchday_datepicker;
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn declaration_status_column;
    @javafx.fxml.FXML
    private TableView player_medical_fitness_tableview;
    @javafx.fxml.FXML
    private TableColumn match_day_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void gameplay_declaration_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }
}