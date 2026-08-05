package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SellTicketController {

    @FXML
    private TableView<ScheduleModel> scheduleTable;
    @FXML
    private TableColumn<ScheduleModel, String> matchColumn;
    @FXML
    private TableColumn<ScheduleModel, String> dateColumn;
    @FXML
    private TableColumn<ScheduleModel, String> venueColumn;
    @FXML
    private TableColumn<ScheduleModel, Integer> availableSeatsColumn;
    @FXML
    private ComboBox<String> matchComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private Label statusLabel;

    private final ArrayList<ScheduleModel> allMatches = new ArrayList<>();

    @FXML
    public void initialize() {

        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        availableSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));

        allMatches.add(new ScheduleModel("Bashundhara Kings vs Abahani Limited", "10 August 2026", "Bangabandhu Stadium", 3200));
        allMatches.add(new ScheduleModel("Mohammedan SC vs Sheikh Russel", "15 August 2026", "Sylhet Stadium", 4000));

        scheduleTable.setItems(FXCollections.observableArrayList(allMatches));

        matchComboBox.getItems().addAll(
                "Bashundhara Kings vs Abahani Limited",
                "Mohammedan SC vs Sheikh Russel"
        );
    }

    @FXML
    public void confirmSale() {

        String selectedMatch = matchComboBox.getValue();
        String quantityText = quantityField.getText();

        if (selectedMatch == null) {
            statusLabel.setText("Please select a match.");
            return;
        }

        if (quantityText.isEmpty()) {
            statusLabel.setText("Please enter the ticket quantity.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Quantity must be a whole number.");
            return;
        }

        if (quantity <= 0) {
            statusLabel.setText("Quantity must be greater than zero.");
            return;
        }

        ScheduleModel found = null;
        for (ScheduleModel m : allMatches) {
            if (m.getMatch().equals(selectedMatch)) {
                found = m;
            }
        }

        if (found == null) {
            statusLabel.setText("Match not found.");
            return;
        }

        if (quantity > found.getAvailableSeats()) {
            statusLabel.setText("Not enough seats available. Only " + found.getAvailableSeats() + " left.");
            return;
        }

        found.setAvailableSeats(found.getAvailableSeats() - quantity);

        scheduleTable.setItems(FXCollections.observableArrayList(allMatches));
        quantityField.clear();

        statusLabel.setText(quantity + " ticket(s) sold for " + selectedMatch + ".");
    }

    @FXML
    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/TicketManagerDashboard.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Ticket Manager Dashboard");
        stage.show();
    }
}