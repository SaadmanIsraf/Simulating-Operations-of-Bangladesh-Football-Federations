package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FixturesController
{
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> homeTeamTC;
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> awayTeamTC;
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> fixtureDateTC;
    @javafx.fxml.FXML
    private TextArea viewFixtureTA;
    @javafx.fxml.FXML
    private TextField homeTeamTF;
    @javafx.fxml.FXML
    private TextField awayTeamTF;
    @javafx.fxml.FXML
    private DatePicker fixtureDateDP;
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> matchRoundTC;
    @javafx.fxml.FXML
    private TableView<Fixture> CreateFixtureTV;
    @javafx.fxml.FXML
    private Label messageLabel2;
    @javafx.fxml.FXML
    private Label messageLabel1;
    @javafx.fxml.FXML
    private TextField competitionNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> matchRoundCB;
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> competitionNameTC;
    @javafx.fxml.FXML
    private TextField fixtureIdTF;
    @javafx.fxml.FXML
    private TableColumn<Fixture,String> fixtureIdTC;
    private ObservableList<Fixture> fixtureList =
            FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        fixtureIdTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFixtureId()
                )
        );

        homeTeamTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getHomeTeam()
                )
        );

        awayTeamTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAwayTeam()
                )
        );

        competitionNameTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCompetitionName()
                )
        );

        fixtureDateTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFixtureDate()
                )
        );

        matchRoundTC.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getMatchRound()
                )
        );

        // Connect the list to the TableView
        CreateFixtureTV.setItems(fixtureList);

        // ComboBox options
        matchRoundCB.setItems(FXCollections.observableArrayList(
                "Group Stage",
                "RO-16",
                "Quarterfinal",
                "Semifinal",
                "Final"
        ));

        // Load previously saved fixtures
        loadFixtures();

    }

    @javafx.fxml.FXML
    public void updateFixtureButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void deleteFixtureButton(ActionEvent actionEvent) {
        Fixture selectedFixture =
                CreateFixtureTV.getSelectionModel().getSelectedItem();

        // Check if a row is selected
        if (selectedFixture == null) {
            messageLabel1.setText("Please select a fixture to delete.");
            return;
        }

        // Remove the selected fixture from the list
        fixtureList.remove(selectedFixture);

        // Save the updated list to the bin file
        saveFixtures();

        messageLabel1.setText("Fixture deleted successfully!");
    }

    @javafx.fxml.FXML
    public void viewFixtureButton(ActionEvent actionEvent) {
        Fixture selectedFixture =
                CreateFixtureTV.getSelectionModel().getSelectedItem();

        // Check if a row is selected
        if (selectedFixture == null) {
            messageLabel2.setText("Please select a fixture to view.");
            return;
        }

        // Show fixture details in TextArea
        viewFixtureTA.setText(
                "Fixture ID: " + selectedFixture.getFixtureId() + "\n" +
                        "Competition: " + selectedFixture.getCompetitionName() + "\n" +
                        "Home Team: " + selectedFixture.getHomeTeam() + "\n" +
                        "Away Team: " + selectedFixture.getAwayTeam() + "\n" +
                        "Fixture Date: " + selectedFixture.getFixtureDate() + "\n" +
                        "Match Round: " + selectedFixture.getMatchRound()
        );
    }

    @javafx.fxml.FXML
    public void createFixtureButton(ActionEvent actionEvent) {
        String fixtureId = fixtureIdTF.getText();
        String competitionName = competitionNameTF.getText();
        String homeTeam = homeTeamTF.getText();
        String awayTeam = awayTeamTF.getText();

        String fixtureDate = "";

        if (fixtureDateDP.getValue() != null) {
            fixtureDate = fixtureDateDP.getValue().toString();
        }

        String matchRound = "";

        if (matchRoundCB.getValue() != null) {
            matchRound = matchRoundCB.getValue().toString();
        }

        // Check if fields are empty
        if (fixtureId.isEmpty() ||
                competitionName.isEmpty() ||
                homeTeam.isEmpty() ||
                awayTeam.isEmpty() ||
                fixtureDate.isEmpty() ||
                matchRound.isEmpty()) {

            messageLabel1.setText("Please fill in all fields.");
            return;
        }

        // Create Fixture object
        Fixture fixture = new Fixture(
                fixtureId,
                homeTeam,
                awayTeam,
                competitionName,
                fixtureDate,
                matchRound
        );

        // Add fixture to TableView
        fixtureList.add(fixture);

        // Save to bin file
        saveFixtures();

        // Clear fields
        fixtureIdTF.clear();
        competitionNameTF.clear();
        homeTeamTF.clear();
        awayTeamTF.clear();
        fixtureDateDP.setValue(null);
        matchRoundCB.setValue(null);

        messageLabel1.setText("Fixture created successfully!");
    }
    private void saveFixtures() {

        try {

            FileOutputStream fileOut =
                    new FileOutputStream("fixtures.bin");

            ObjectOutputStream objectOut =
                    new ObjectOutputStream(fileOut);

            objectOut.writeObject(
                    new ArrayList<>(fixtureList)
            );

            objectOut.close();
            fileOut.close();

        } catch (IOException e) {

            e.printStackTrace();

            messageLabel1.setText(
                    "Error saving fixture."
            );
        }
    }
    private void loadFixtures() {

        File file = new File("fixtures.bin");

        if (!file.exists()) {
            return;
        }

        try {

            FileInputStream fileIn =
                    new FileInputStream(file);

            ObjectInputStream objectIn =
                    new ObjectInputStream(fileIn);

            ArrayList<Fixture> loadedFixtures =
                    (ArrayList<Fixture>) objectIn.readObject();

            fixtureList.setAll(loadedFixtures);

            objectIn.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/Match_management/MatchmanagementDashboard.fxml");
    }
}



