package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FootballAcademicsController
{   private ObservableList<Academy> academyList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn <Academy, String>academyNameTC;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> academyLocationTC;
    @javafx.fxml.FXML
    private TextField academyLocationTF;
    @javafx.fxml.FXML
    private TextField coachNumberTF;
    @javafx.fxml.FXML
    private TextArea displayAcademyTA;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> academyTypeTC;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> academyIdTC;
    @javafx.fxml.FXML
    private TextField academyIdTF;
    @javafx.fxml.FXML
    private ComboBox<String> academyTypeTF;
    @javafx.fxml.FXML
    private Label messageLabel2;
    @javafx.fxml.FXML
    private TextField academyNameTF;
    @javafx.fxml.FXML
    private Label messageLabel1;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> contactTC;
    @javafx.fxml.FXML
    private TableView<Academy> registerAcademyTV;
    @javafx.fxml.FXML
    private TextField headCoachTF;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> numberOfPlayersTC;
    @javafx.fxml.FXML
    private TableColumn<Academy, String> headCoachTC;
    @javafx.fxml.FXML
    private TextField numberOfPlayersTF;

    @javafx.fxml.FXML
    public void initialize() {
        academyIdTC.setCellValueFactory(
                new PropertyValueFactory<>("academyId"));

        academyNameTC.setCellValueFactory(
                new PropertyValueFactory<>("academyName"));

        academyLocationTC.setCellValueFactory(
                new PropertyValueFactory<>("academyLocation"));

        academyTypeTC.setCellValueFactory(
                new PropertyValueFactory<>("academyType"));

        headCoachTC.setCellValueFactory(
                new PropertyValueFactory<>("headCoach"));

        contactTC.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber"));

        numberOfPlayersTC.setCellValueFactory(
                new PropertyValueFactory<>("numberOfPlayers"));

        registerAcademyTV.setItems(academyList);
    }

    @javafx.fxml.FXML
    public void chechExpiryButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void displayAcademyButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateAcademyButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerButton(ActionEvent actionEvent) {
        String academyId = academyIdTF.getText();
        String academyName = academyNameTF.getText();
        String academyLocation = academyLocationTF.getText();
        String academyType = academyTypeTF.getValue();
        String headCoach = headCoachTF.getText();
        String contactNumber = coachNumberTF.getText();
        String numberOfPlayers = numberOfPlayersTF.getText();

        Academy academy = new Academy(
                academyId,
                academyName,
                academyLocation,
                academyType,
                headCoach,
                contactNumber,
                numberOfPlayers
        );
        academyList.add(academy);

        // Clear the input fields after registration
        academyIdTF.clear();
        academyNameTF.clear();
        academyLocationTF.clear();
        academyTypeTF.setValue(null);
        headCoachTF.clear();
        coachNumberTF.clear();
        numberOfPlayersTF.clear();

    }

    @javafx.fxml.FXML
    public void updateLicenseButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }
}