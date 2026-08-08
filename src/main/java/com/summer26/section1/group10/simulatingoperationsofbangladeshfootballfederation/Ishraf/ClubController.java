package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

public class ClubController
{
    @javafx.fxml.FXML
    private TextField clubNameTF;
    @javafx.fxml.FXML
    private TextField clubIdTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void searchButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void transferPlayerButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void manageAcademyButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/FootballAcademics.fxml");
    }

    @javafx.fxml.FXML
    public void assignManagerButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addplayerButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/ClubLicensing.fxml");
    }

    @javafx.fxml.FXML
    public void removeplayerButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
    }
}