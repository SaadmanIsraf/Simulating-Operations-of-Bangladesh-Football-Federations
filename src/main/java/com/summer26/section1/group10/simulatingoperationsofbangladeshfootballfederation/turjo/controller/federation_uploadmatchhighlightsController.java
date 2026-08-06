package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchHighlight;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchHighlightManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_uploadmatchhighlightsController {

    @FXML
    private TextField matchIdTF;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField videoLinkTF;

    @FXML
    private TextField durationTF;

    @FXML
    private ComboBox<String> highlightTypeCB;

    @FXML
    private ComboBox<String> publishStatusCB;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private TableView<MatchHighlight> highlightTable;

    @FXML
    private TableColumn<MatchHighlight, String> matchIdCol;

    @FXML
    private TableColumn<MatchHighlight, String> titleCol;

    @FXML
    private TableColumn<MatchHighlight, String> typeCol;

    @FXML
    private TableColumn<MatchHighlight, String> durationCol;

    @FXML
    private TableColumn<MatchHighlight, String> statusCol;

    @FXML
    private TableColumn<MatchHighlight, String> videoLinkCol;

    @FXML
    public void initialize() {

        highlightTypeCB.getItems().addAll(
                "Goals",
                "Skills",
                "Saves",
                "Full Match",
                "Best Moments"
        );

        publishStatusCB.getItems().addAll(
                "Published",
                "Draft"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("highlightType"));

        durationCol.setCellValueFactory(
                new PropertyValueFactory<>("duration"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("publishStatus"));

        videoLinkCol.setCellValueFactory(
                new PropertyValueFactory<>("videoLink"));

        loadHighlights();
    }

    private void loadHighlights() {

        MatchHighlightManager.loadFromFile();

        highlightTable.getItems().setAll(
                MatchHighlightManager.getHighlightList());

        highlightTable.refresh();
    }

    @FXML
    public void uploadButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String title = titleTF.getText().trim();
        String videoLink = videoLinkTF.getText().trim();
        String duration = durationTF.getText().trim();
        String highlightType = highlightTypeCB.getValue();
        String publishStatus = publishStatusCB.getValue();
        String description = descriptionTA.getText().trim();

        if (matchId.isEmpty()
                || title.isEmpty()
                || videoLink.isEmpty()
                || duration.isEmpty()
                || highlightType == null
                || publishStatus == null
                || description.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        MatchHighlight highlight = new MatchHighlight(
                matchId,
                title,
                videoLink,
                duration,
                highlightType,
                publishStatus,
                description
        );

        MatchHighlightManager.addHighlight(highlight);
        MatchHighlightManager.saveToFile();

        loadHighlights();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Match highlight uploaded successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/federation_dashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        titleTF.clear();
        videoLinkTF.clear();
        durationTF.clear();

        highlightTypeCB.setValue(null);
        publishStatusCB.setValue(null);

        descriptionTA.clear();

        highlightTable.getSelectionModel().clearSelection();
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