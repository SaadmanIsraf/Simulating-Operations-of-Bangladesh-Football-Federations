package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class matchofficial_dashboardController implements UserReceiver
{

    private MatchOfficials loggedInUser;
    @Override
    public void setLoggedInUser(User user){
        if (user instanceof MatchOfficials m){
            loggedInUser = m;
        }
        else {
            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
        }
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void registerOfficialOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/registarasmatchofficial.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void verifyPlayerEligibilityOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/verifyplayereligibility.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void requestRefereeReplacementOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/requestrefreereplacement.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void logoutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewAssignedMatchesOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/assignedmatches.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void submitMatchReportOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/submitmatchreport.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void recordFoulsCardsOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/recordfoulsandcards.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void viewMatchScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/viewmatchschedule.fxml", loggedInUser);
    }

    @javafx.fxml.FXML
    public void logVARReportOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/lobvarreport.fxml", loggedInUser);
    }

    @Deprecated
    public void viewPlayerCardsOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/registarasmatchofficial.fxml", loggedInUser);
    }

    @Deprecated
    public void viewMatchHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/registarasmatchofficial.fxml", loggedInUser);
    }

    @Deprecated
    public void recordMatchEventsOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/updatematchstatus.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Deprecated
    public void updateMatchStatusOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/updatematchstatus.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}