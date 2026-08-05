package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SpectatorDashboardController {

    @FXML
    public void openMatch(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Match.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Match Schedule");
        stage.show();
    }

    @FXML
    public void openStandings(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Standings.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Standings");
        stage.show();
    }

    @FXML
    public void openTeam(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Team.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Team Information");
        stage.show();
    }

    @FXML
    public void openPlayer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Player.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Player Information");
        stage.show();
    }

    @FXML
    public void openTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Tournament.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Tournament Information");
        stage.show();
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/login.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Login");
        stage.show();
    }
}