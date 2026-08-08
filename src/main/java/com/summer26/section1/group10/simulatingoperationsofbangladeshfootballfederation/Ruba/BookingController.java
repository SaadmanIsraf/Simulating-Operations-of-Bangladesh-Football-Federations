package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BookingController {

    private static final String BOOKING_FILE = "bookings.bin";

    @javafx.fxml.FXML
    private TextField bookingIdField;
    @javafx.fxml.FXML
    private Label matchLabel;
    @javafx.fxml.FXML
    private Label customerLabel;
    @javafx.fxml.FXML
    private Label quantityLabel;
    @javafx.fxml.FXML
    private Label statusValueLabel;
    @javafx.fxml.FXML
    private Label statusLabel;

    private ArrayList<BookingModel> allBookings = new ArrayList<>();

    private BookingModel selectedBooking;

    @javafx.fxml.FXML
    public void initialize() {

        allBookings = FileStorageUtil.loadData(BOOKING_FILE);

        if (allBookings.isEmpty()) {
            allBookings.add(new BookingModel("BK001", "Bashundhara Kings vs Abahani Limited", "Rahim Uddin", 2, "Booked"));
            allBookings.add(new BookingModel("BK002", "Mohammedan SC vs Sheikh Russel", "Karim Ahmed", 4, "Booked"));
            allBookings.add(new BookingModel("BK003", "Bashundhara Kings vs Abahani Limited", "Nusrat Jahan", 1, "Cancelled"));
            FileStorageUtil.saveData(BOOKING_FILE, allBookings);
        }

        clearDetails();
    }

    @javafx.fxml.FXML
    public void searchBooking() {

        String bookingId = bookingIdField.getText();

        if (bookingId.isEmpty()) {
            statusLabel.setText("Please enter a booking ID.");
            clearDetails();
            return;
        }

        BookingModel found = null;
        for (BookingModel b : allBookings) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                found = b;
            }
        }

        if (found == null) {
            statusLabel.setText("No booking found with ID: " + bookingId);
            clearDetails();
            return;
        }

        selectedBooking = found;
        matchLabel.setText(found.getMatch());
        customerLabel.setText(found.getCustomerName());
        quantityLabel.setText(String.valueOf(found.getQuantity()));
        statusValueLabel.setText(found.getStatus());
        statusLabel.setText("Booking found.");
    }

    @javafx.fxml.FXML
    public void cancelBooking() {

        if (selectedBooking == null) {
            statusLabel.setText("Please search for a booking first.");
            return;
        }

        if (selectedBooking.getStatus().equals("Cancelled") || selectedBooking.getStatus().equals("Refunded")) {
            statusLabel.setText("This booking is already " + selectedBooking.getStatus().toLowerCase() + ".");
            return;
        }

        selectedBooking.setStatus("Cancelled");
        statusValueLabel.setText(selectedBooking.getStatus());

        FileStorageUtil.saveData(BOOKING_FILE, allBookings);

        statusLabel.setText("Booking cancelled. " + selectedBooking.getQuantity() + " seat(s) restored to availability.");
    }

    @javafx.fxml.FXML
    public void refundBooking() {

        if (selectedBooking == null) {
            statusLabel.setText("Please search for a booking first.");
            return;
        }

        if (!selectedBooking.getStatus().equals("Booked") && !selectedBooking.getStatus().equals("Cancelled")) {
            statusLabel.setText("This booking is not eligible for a refund.");
            return;
        }

        selectedBooking.setStatus("Refunded");
        statusValueLabel.setText(selectedBooking.getStatus());

        FileStorageUtil.saveData(BOOKING_FILE, allBookings);

        statusLabel.setText("Refund processed. " + selectedBooking.getQuantity() + " seat(s) restored to availability.");
    }

    private void clearDetails() {
        selectedBooking = null;
        matchLabel.setText("-");
        customerLabel.setText("-");
        quantityLabel.setText("-");
        statusValueLabel.setText("-");
    }

    @javafx.fxml.FXML
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