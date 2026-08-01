package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class tournament_rules_controller
{
    @javafx.fxml.FXML
    private TextArea tournament_rules_textarea;
    @javafx.fxml.FXML
    private TextArea match_regulations_textarea;
    @javafx.fxml.FXML
    private ComboBox tournament_combobox;
    @javafx.fxml.FXML
    private TextArea code_of_conduct_textarea;
    @javafx.fxml.FXML
    private Label information_label;
    @javafx.fxml.FXML
    private TextArea disciplinary_guidelines_textarea;
    @javafx.fxml.FXML
    private TextArea eligibility_criteria_textarea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refresh_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void tournament_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}