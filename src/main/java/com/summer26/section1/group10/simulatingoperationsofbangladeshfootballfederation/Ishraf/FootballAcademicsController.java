package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

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
                data -> new SimpleStringProperty(
                        data.getValue().getAcademyId()
                )
        );

        academyNameTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getAcademyName()
                )
        );

        academyLocationTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getAcademyLocation()
                )
        );

        academyTypeTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getAcademyType()
                )
        );

        headCoachTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getHeadCoach()
                )
        );

        contactTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getContactNumber()
                )
        );

        numberOfPlayersTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNumberOfPlayers()
                )
        );

        // Connect the list to the TableView
        registerAcademyTV.setItems(academyList);
        // Academy Type ComboBox
        academyTypeTF.setItems(
                FXCollections.observableArrayList(
                        "Youth",
                        "Main Team"
                )
        );

    }

    @javafx.fxml.FXML
    public void chechExpiryButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void displayAcademyButton(ActionEvent actionEvent) {
        Academy selectedAcademy =
                registerAcademyTV.getSelectionModel().getSelectedItem();

        // Check if a row is selected
        if (selectedAcademy == null) {
            messageLabel2.setText("Please select an academy to display.");
            return;
        }

        // Display academy details in TextArea
        displayAcademyTA.setText(
                "Academy ID: " + selectedAcademy.getAcademyId() + "\n" +
                        "Academy Name: " + selectedAcademy.getAcademyName() + "\n" +
                        "Location: " + selectedAcademy.getAcademyLocation() + "\n" +
                        "Academy Type: " + selectedAcademy.getAcademyType() + "\n" +
                        "Head Coach: " + selectedAcademy.getHeadCoach() + "\n" +
                        "Contact Number: " + selectedAcademy.getContactNumber() + "\n" +
                        "Number of Players: " + selectedAcademy.getNumberOfPlayers()
        );
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
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
    }
}