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

public class SeatManagementController {

    @FXML
    private ComboBox<String> matchComboBox;
    @FXML
    private TextField availableSeatsField;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<SeatModel> seatTable;
    @FXML
    private TableColumn<SeatModel, String> matchColumn;
    @FXML
    private TableColumn<SeatModel, Integer> totalSeatsColumn;
    @FXML
    private TableColumn<SeatModel, Integer> availableSeatsColumn;

    private final ArrayList<SeatModel> allSeats = new ArrayList<>();

    @FXML
    public void initialize() {

        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));
        totalSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("totalSeats"));
        availableSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));

        matchComboBox.getItems().addAll(
                "Bashundhara Kings vs Abahani Limited",
                "Mohammedan SC vs Sheikh Russel"
        );

        allSeats.add(new SeatModel("Bashundhara Kings vs Abahani Limited", 5000, 3200));
        allSeats.add(new SeatModel("Mohammedan SC vs Sheikh Russel", 4000, 4000));

        seatTable.setItems(FXCollections.observableArrayList(allSeats));
    }

    @FXML
    public void updateSeats() {

        String selectedMatch = matchComboBox.getValue();
        String seatsText = availableSeatsField.getText();

        if (selectedMatch == null) {
            statusLabel.setText("Please select a match first.");
            return;
        }

        if (seatsText.isEmpty()) {
            statusLabel.setText("Please enter the number of available seats.");
            return;
        }

        int newAvailableSeats;
        try {
            newAvailableSeats = Integer.parseInt(seatsText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Available seats must be a whole number.");
            return;
        }

        if (newAvailableSeats < 0) {
            statusLabel.setText("Available seats cannot be negative.");
            return;
        }

        SeatModel found = null;
        for (SeatModel s : allSeats) {
            if (s.getMatch().equals(selectedMatch)) {
                found = s;
            }
        }

        if (found == null) {
            statusLabel.setText("No seat record found for this match.");
            return;
        }

        if (newAvailableSeats > found.getTotalSeats()) {
            statusLabel.setText("Available seats cannot exceed total seats (" + found.getTotalSeats() + ").");
            return;
        }

        found.setAvailableSeats(newAvailableSeats);
        seatTable.setItems(FXCollections.observableArrayList(allSeats));
        availableSeatsField.clear();

        statusLabel.setText("Seat availability updated for " + selectedMatch + ".");
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