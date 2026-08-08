package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;

public class MatchmanagementDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void fixturesButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/Match_management/Fixtures.fxml");
    }
}