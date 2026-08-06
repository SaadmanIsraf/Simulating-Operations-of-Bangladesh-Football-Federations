package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.match_official;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class matchofficial_dashboardController implements UserReceiver {

    private match_official loggedInUser;

    @Override
    public void setLoggedInUser(User user) {

        if (user instanceof match_official m) {
            loggedInUser = m;
        } else {
            AlertGenerator.showAlert(
                    "Error",
                    "This is not a valid user for this page."
            );
        }
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void registerOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/register.fxml",
                loggedInUser);
    }

    @FXML
    public void viewmatchscheduleOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/viewmatchschedule.fxml",
                loggedInUser);
    }

    @FXML
    public void assignedmatchesOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/assignedmatches.fxml",
                loggedInUser);
    }

    @FXML
    public void verifyplayereligibilityOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/verifyplayereligibility.fxml",
                loggedInUser);
    }

    @FXML
    public void recordfoulscardsOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/recordfoulscards.fxml",
                loggedInUser);
    }

    @FXML
    public void submitmatchreportOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/submitmatchreport.fxml",
                loggedInUser);
    }

    @FXML
    public void logvarreportOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/logvarreport.fxml",
                loggedInUser);
    }

    @FXML
    public void requestrefereereplacementOA(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchSceneWithData(
                actionEvent,
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_official/requestrefereereplacement.fxml",
                loggedInUser);
    }

    @FXML
    public void logoutOA(ActionEvent actionEvent) {

        SceneSwitcher.switchTo("Login.fxml");
    }
}