package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketManagerDashboardController {

    @FXML
    public void openCreateTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/CreateTicket.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Create Ticket");
        stage.show();
    }

    @FXML
    public void openSalesReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SalesReport.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Sales Report");
        stage.show();
    }

    @FXML
    public void openSeatManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SeatManagement.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Seat Management");
        stage.show();
    }

    @FXML
    public void openSellTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SellTicket.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Match Schedule & Sell Tickets");
        stage.show();
    }

    @FXML
    public void openBooking(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/Booking.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Cancel / Refund Booking");
        stage.show();
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/login.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Login");
        stage.show();
    }
}