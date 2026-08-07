package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BffManagerDashBoardController
{
    @javafx.fxml.FXML
    private TableColumn homeTeamTC;
    @javafx.fxml.FXML
    private TableColumn awayTeamTC;
    @javafx.fxml.FXML
    private TextField playerAddedTF;
    @javafx.fxml.FXML
    private TableColumn activityTC;
    @javafx.fxml.FXML
    private TextField matchScheduledTF;
    @javafx.fxml.FXML
    private TableColumn matchdateTC;
    @javafx.fxml.FXML
    private TextField managerIdTF;
    @javafx.fxml.FXML
    private TableColumn stadiumTC;
    @javafx.fxml.FXML
    private TextField coachDeletedTF;
    @javafx.fxml.FXML
    private TableColumn timeTC;
    @javafx.fxml.FXML
    private TableColumn userTC;
    @javafx.fxml.FXML
    private TableView recentActivityTV;
    @javafx.fxml.FXML
    private TableColumn activitydateTC;
    @javafx.fxml.FXML
    private TextField tournamentCreatedTF;
    @javafx.fxml.FXML
    private TableColumn statusTC;
    @javafx.fxml.FXML
    private TableView upcomingMatchesTV;
    @javafx.fxml.FXML
    private TableColumn matchIdTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ManageSponsorshipButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/ManageSponsorship.fxml");
    }

    @javafx.fxml.FXML
    public void ManageAcademyButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/FootballAcademics.fxml");
    }

    @javafx.fxml.FXML
    public void ManageNationalTeamButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/NationalTeam.fxml");
    }

    @javafx.fxml.FXML
    public void searchButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ManageWomenDevelopmentButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/WomenFootball.fxml");
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Login.fxml");

    }

    @javafx.fxml.FXML
    public void ManageRefereeButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void RegisterClubButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/ClubLicensing.fxml");
    }

    @javafx.fxml.FXML
    public void ManageClubButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/Club.fxml");

    }

    @javafx.fxml.FXML
    public void CreateYouthProgramButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/YouthProgram.fxml");
    }
}