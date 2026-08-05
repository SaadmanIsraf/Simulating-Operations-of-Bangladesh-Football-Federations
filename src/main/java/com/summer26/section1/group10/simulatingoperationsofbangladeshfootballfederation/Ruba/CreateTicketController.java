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

public class CreateTicketController {

    @FXML
    private ComboBox<String> matchComboBox;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<TicketModel> ticketTable;
    @FXML
    private TableColumn<TicketModel, String> matchColumn;
    @FXML
    private TableColumn<TicketModel, String> categoryColumn;
    @FXML
    private TableColumn<TicketModel, Double> priceColumn;
    @FXML
    private TableColumn<TicketModel, Integer> quantityColumn;

    private final ArrayList<TicketModel> allTickets = new ArrayList<>();

    @FXML
    public void initialize() {

        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        matchComboBox.getItems().addAll(
                "Bashundhara Kings vs Abahani Limited",
                "Mohammedan SC vs Sheikh Russel"
        );

        categoryComboBox.getItems().addAll(
                "VIP",
                "General",
                "Student"
        );

        ticketTable.setItems(FXCollections.observableArrayList(allTickets));
    }

    @FXML
    public void saveTicket() {

        String match = matchComboBox.getValue();
        String category = categoryComboBox.getValue();
        String priceText = priceField.getText();
        String quantityText = quantityField.getText();

        if (match == null || category == null || priceText.isEmpty() || quantityText.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceText);
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Price must be a number and quantity must be a whole number.");
            return;
        }

        if (price <= 0 || quantity <= 0) {
            statusLabel.setText("Price and quantity must be greater than zero.");
            return;
        }

        TicketModel newTicket = new TicketModel(match, category, price, quantity);
        allTickets.add(newTicket);

        ticketTable.setItems(FXCollections.observableArrayList(allTickets));

        priceField.clear();
        quantityField.clear();

        statusLabel.setText("Ticket created successfully for " + match + ".");
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