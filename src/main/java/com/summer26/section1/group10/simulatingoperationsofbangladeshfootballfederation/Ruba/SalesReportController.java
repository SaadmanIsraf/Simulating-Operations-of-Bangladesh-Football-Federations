package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesReportController {

    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private Label totalTicketsLabel;
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private TableView<SaleModel> saleTable;
    @javafx.fxml.FXML
    private TableColumn<SaleModel, LocalDate> dateColumn;
    @javafx.fxml.FXML
    private TableColumn<SaleModel, String> matchColumn;
    @javafx.fxml.FXML
    private TableColumn<SaleModel, Integer> ticketsSoldColumn;
    @javafx.fxml.FXML
    private TableColumn<SaleModel, Double> revenueColumn;

    private final ArrayList<SaleModel> allSales = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));
        ticketsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        LocalDate today = LocalDate.now();

        allSales.add(new SaleModel(today, "Bashundhara Kings vs Abahani Limited", 120, 60000));
        allSales.add(new SaleModel(today.minusDays(2), "Mohammedan SC vs Sheikh Russel", 80, 32000));
        allSales.add(new SaleModel(today.minusDays(6), "Bashundhara Kings vs Mohammedan SC", 150, 75000));
        allSales.add(new SaleModel(today.minusDays(15), "Abahani Limited vs Sheikh Russel", 90, 40500));

        showResults(allSales);
    }

    @javafx.fxml.FXML
    public void generateReport() {

        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (start == null || end == null) {
            statusLabel.setText("Please select both a start and end date.");
            return;
        }

        if (start.isAfter(end)) {
            statusLabel.setText("Start date must be before the end date.");
            return;
        }

        ArrayList<SaleModel> result = new ArrayList<>();
        for (SaleModel s : allSales) {
            if (!s.getDate().isBefore(start) && !s.getDate().isAfter(end)) {
                result.add(s);
            }
        }

        showResults(result);
        statusLabel.setText("Report generated from " + start + " to " + end + ".");
    }

    @javafx.fxml.FXML
    public void showTodaySummary() {

        LocalDate today = LocalDate.now();

        ArrayList<SaleModel> result = new ArrayList<>();
        for (SaleModel s : allSales) {
            if (s.getDate().equals(today)) {
                result.add(s);
            }
        }

        showResults(result);
        statusLabel.setText("Showing today's sales summary.");
    }

    @javafx.fxml.FXML
    public void showWeekSummary() {

        LocalDate weekAgo = LocalDate.now().minusDays(7);

        ArrayList<SaleModel> result = new ArrayList<>();
        for (SaleModel s : allSales) {
            if (!s.getDate().isBefore(weekAgo)) {
                result.add(s);
            }
        }

        showResults(result);
        statusLabel.setText("Showing this week's sales summary.");
    }

    private void showResults(ArrayList<SaleModel> sales) {

        saleTable.setItems(FXCollections.observableArrayList(sales));

        int totalTickets = 0;
        double totalRevenue = 0;

        for (SaleModel s : sales) {
            totalTickets = totalTickets + s.getTicketsSold();
            totalRevenue = totalRevenue + s.getRevenue();
        }

        totalTicketsLabel.setText("Total Tickets Sold: " + totalTickets);
        totalRevenueLabel.setText("Total Revenue: " + totalRevenue);
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