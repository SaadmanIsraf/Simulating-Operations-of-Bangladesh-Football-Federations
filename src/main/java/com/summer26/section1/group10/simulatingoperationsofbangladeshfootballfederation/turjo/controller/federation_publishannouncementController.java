package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Announcement;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.AnnouncementManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class federation_publishannouncementController {

    @FXML
    private TextField announcementIdTF;

    @FXML
    private TextField titleTF;

    @FXML
    private ComboBox<String> competitionCB;

    @FXML
    private TextField matchTF;

    @FXML
    private TextField venueTF;

    @FXML
    private DatePicker matchDateDP;

    @FXML
    private TextField matchTimeTF;

    @FXML
    private TextField sponsorTF;

    @FXML
    private TextArea detailsTA;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TableView<Announcement> announcementTable;

    @FXML
    private TableColumn<Announcement, String> announcementIdCol;

    @FXML
    private TableColumn<Announcement, String> titleCol;

    @FXML
    private TableColumn<Announcement, String> competitionCol;

    @FXML
    private TableColumn<Announcement, String> matchCol;

    @FXML
    private TableColumn<Announcement, LocalDate> dateCol;

    @FXML
    private TableColumn<Announcement, String> venueCol;

    @FXML
    private TableColumn<Announcement, String> statusCol;

    @FXML
    public void initialize() {

        competitionCB.getItems().addAll(
                "Bangladesh Premier League",
                "Federation Cup",
                "Independence Cup",
                "International Friendly"
        );

        statusCB.getItems().addAll(
                "Published",
                "Draft"
        );

        announcementIdCol.setCellValueFactory(
                new PropertyValueFactory<>("announcementId"));

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        competitionCol.setCellValueFactory(
                new PropertyValueFactory<>("competition"));

        matchCol.setCellValueFactory(
                new PropertyValueFactory<>("match"));

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("matchDate"));

        venueCol.setCellValueFactory(
                new PropertyValueFactory<>("venue"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("publishStatus"));

        loadAnnouncements();
    }

    private void loadAnnouncements() {

        AnnouncementManager.loadFromFile();

        announcementTable.getItems().setAll(
                AnnouncementManager.getAnnouncementList());

        announcementTable.refresh();
    }

    @FXML
    public void publishButtonOnAction(ActionEvent actionEvent) {

        String announcementId = announcementIdTF.getText().trim();
        String title = titleTF.getText().trim();
        String competition = competitionCB.getValue();
        String match = matchTF.getText().trim();
        String venue = venueTF.getText().trim();
        LocalDate matchDate = matchDateDP.getValue();
        String matchTime = matchTimeTF.getText().trim();
        String sponsor = sponsorTF.getText().trim();
        String details = detailsTA.getText().trim();
        String publishStatus = statusCB.getValue();

        if (announcementId.isEmpty()
                || title.isEmpty()
                || competition == null
                || match.isEmpty()
                || venue.isEmpty()
                || matchDate == null
                || matchTime.isEmpty()
                || sponsor.isEmpty()
                || details.isEmpty()
                || publishStatus == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        Announcement announcement = new Announcement(
                announcementId,
                title,
                competition,
                match,
                venue,
                matchDate,
                matchTime,
                sponsor,
                details,
                publishStatus
        );

        AnnouncementManager.addAnnouncement(announcement);
        AnnouncementManager.saveToFile();

        loadAnnouncements();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Announcement published successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/dashboardView.fxml"
        );
    }

    private void clearFields() {

        announcementIdTF.clear();
        titleTF.clear();

        competitionCB.setValue(null);

        matchTF.clear();
        venueTF.clear();

        matchDateDP.setValue(null);

        matchTimeTF.clear();
        sponsorTF.clear();

        detailsTA.clear();

        statusCB.setValue(null);

        announcementTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType,
                           String title,
                           String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}