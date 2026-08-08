package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

    private static final String TICKET_FILE = "tickets.bin";

    @javafx.fxml.FXML
    private ComboBox<String> matchComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> categoryComboBox;
    @javafx.fxml.FXML
    private TextField priceField;
    @javafx.fxml.FXML
    private TextField quantityField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableView<TicketModel> ticketTable;
    @javafx.fxml.FXML
    private TableColumn<TicketModel, String> matchColumn;
    @javafx.fxml.FXML
    private TableColumn<TicketModel, String> categoryColumn;
    @javafx.fxml.FXML
    private TableColumn<TicketModel, Double> priceColumn;
    @javafx.fxml.FXML
    private TableColumn<TicketModel, Integer> quantityColumn;

    private ArrayList<TicketModel> allTickets = new ArrayList<>();

    @javafx.fxml.FXML
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

        allTickets = FileStorageUtil.loadData(TICKET_FILE);

        ticketTable.setItems(FXCollections.observableArrayList(allTickets));
    }

    @javafx.fxml.FXML
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

        FileStorageUtil.saveData(TICKET_FILE, allTickets);

        ticketTable.setItems(FXCollections.observableArrayList(allTickets));

        priceField.clear();
        quantityField.clear();

        statusLabel.setText("Ticket created and saved successfully for " + match + ".");
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