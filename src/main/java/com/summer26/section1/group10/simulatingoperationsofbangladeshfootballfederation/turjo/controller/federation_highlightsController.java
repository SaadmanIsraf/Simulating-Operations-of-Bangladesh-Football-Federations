package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Highlight;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class federation_highlightsController implements UserReceiver {

    @FXML
    private TextField txtHighlightTitle;
    @FXML
    private TableColumn<Highlight, String> videoLinkCol;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableColumn<Highlight, String> highlightTypeCol;
    @FXML
    private TableColumn<Highlight, String> highlightTitleCol;
    @FXML
    private ComboBox<String> cmbCompetition;
    @FXML
    private TableColumn<Highlight, String> matchCol;
    @FXML
    private TextField txtDuration;
    @FXML
    private ComboBox<String> cmbMatch;
    @FXML
    private TableColumn<Highlight, String> durationCol;
    @FXML
    private TableColumn<Highlight, String> competitionCol;
    @FXML
    private Label messageLabel;
    @FXML
    private ComboBox<String> cmbPublishStatus;
    @FXML
    private TextArea txtKeyMoments;
    @FXML
    private TableView<Highlight> highlightsTable;
    @FXML
    private ComboBox<String> cmbHighlightType;
    @FXML
    private TableColumn<Highlight, String> publishStatusCol;
    @FXML
    private TextField txtVideoLink;
    private federation_administrator loggedInUser;
    @Override
    public void setLoggedInUser(User user){
        if (user instanceof federation_administrator f){
            loggedInUser = f;
        }
        else {
            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
        }
    }

    @FXML
    public void initialize() {

        highlightTitleCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("highlighttitle"));
        matchCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("match"));
        competitionCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("competition"));
        highlightTypeCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("highlighttype"));
        durationCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("duration"));
        videoLinkCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("videolink"));
        publishStatusCol.setCellValueFactory(new PropertyValueFactory<Highlight, String>("publishstatus"));

        ArrayList<Object> highlightList = BinaryFileUtility.readObjects("Highlights.bin");
        for (Object record : highlightList) {
            if (record instanceof Highlight highlight) {
                highlightsTable.getItems().add(highlight);
            }
        }

        cmbCompetition.getItems().addAll(
                "Premier League",
                "Federation Cup",
                "Independence Cup"
        );

        cmbMatch.getItems().addAll(
                "Match 101",
                "Match 102",
                "Match 103"
        );

        cmbHighlightType.getItems().addAll(
                "Goal",
                "Save",
                "Skill",
                "Red Card Incident",
                "Full Match Recap"
        );

        cmbPublishStatus.getItems().addAll(
                "Draft",
                "Published",
                "Unpublished"
        );
    }

    private boolean validateInput() {

        if (txtHighlightTitle.getText().trim().isEmpty()) {
            messageLabel.setText("Enter highlight title.");
            txtHighlightTitle.requestFocus();
            return false;
        }

        if (cmbMatch.getValue() == null) {
            messageLabel.setText("Select a match.");
            cmbMatch.requestFocus();
            return false;
        }

        if (cmbCompetition.getValue() == null) {
            messageLabel.setText("Select a competition.");
            cmbCompetition.requestFocus();
            return false;
        }

        if (cmbHighlightType.getValue() == null) {
            messageLabel.setText("Select highlight type.");
            cmbHighlightType.requestFocus();
            return false;
        }

        if (txtDuration.getText().trim().isEmpty()) {
            messageLabel.setText("Enter duration.");
            txtDuration.requestFocus();
            return false;
        }

        if (txtVideoLink.getText().trim().isEmpty()) {
            messageLabel.setText("Enter video link.");
            txtVideoLink.requestFocus();
            return false;
        }

        if (cmbPublishStatus.getValue() == null) {
            messageLabel.setText("Select publish status.");
            cmbPublishStatus.requestFocus();
            return false;
        }

        if (txtDescription.getText().trim().isEmpty()) {
            messageLabel.setText("Enter description.");
            txtDescription.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void uploadHighlightOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        Highlight highlight = new Highlight(
                txtHighlightTitle.getText(),
                cmbMatch.getValue(),
                cmbCompetition.getValue(),
                cmbHighlightType.getValue(),
                txtDuration.getText(),
                txtVideoLink.getText(),
                cmbPublishStatus.getValue(),
                txtDescription.getText(),
                txtKeyMoments.getText());

        highlightsTable.getItems().add(highlight);
        BinaryFileUtility.writeObjects("Highlights.bin", highlight);

        messageLabel.setText("Highlight uploaded successfully.");
    }

    @FXML
    public void updateHighlightOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        Highlight selected = highlightsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a highlight to update.");
            return;
        }

        selected.setHighlighttitle(txtHighlightTitle.getText());
        selected.setMatch(cmbMatch.getValue());
        selected.setCompetition(cmbCompetition.getValue());
        selected.setHighlighttype(cmbHighlightType.getValue());
        selected.setDuration(txtDuration.getText());
        selected.setVideolink(txtVideoLink.getText());
        selected.setPublishstatus(cmbPublishStatus.getValue());
        selected.setDescription(txtDescription.getText());
        selected.setKeymoments(txtKeyMoments.getText());

        highlightsTable.refresh();

        messageLabel.setText("Highlight updated successfully.");
    }

    @FXML
    public void deleteHighlightOA(ActionEvent actionEvent) {

        Highlight selected = highlightsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a highlight to delete.");
            return;
        }

        highlightsTable.getItems().remove(selected);

        messageLabel.setText("Highlight deleted successfully.");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");
    }
}