package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class federation_administrator_approveplayertransferController {

    @FXML
    private ComboBox<String> decisionCB;

    @FXML
    private TextField fromClubTF;

    @FXML
    private TextField toClubTF;

    @FXML
    private TextField playerNameTF;

    @FXML
    private Label messageLabel;


    @FXML
    public void initialize() {
        decisionCB.getItems().addAll(
                "Approved",
                "Rejected",
                "Pending"
        );
    }


    @FXML
    public void verifyTransferOA(ActionEvent actionEvent) {

        String playerName = playerNameTF.getText();
        String fromClub = fromClubTF.getText();
        String toClub = toClubTF.getText();

        if (playerName.isEmpty() || fromClub.isEmpty() || toClub.isEmpty()) {

            messageLabel.setText("Please fill all transfer information");
            messageLabel.setTextFill(Color.RED);

            return;
        }

        messageLabel.setText("Transfer Verified Successfully");
        messageLabel.setTextFill(Color.GREEN);
    }


    @FXML
    public void saveOA(ActionEvent actionEvent) {

        String decision = decisionCB.getValue();

        if (decision == null) {

            messageLabel.setText("Please select a transfer decision");
            messageLabel.setTextFill(Color.RED);

            return;
        }


        if (decision.equals("Approved")) {

            messageLabel.setText("Transfer Approved Successfully");
            messageLabel.setTextFill(Color.GREEN);

        } else if (decision.equals("Rejected")) {

            messageLabel.setText("Transfer Rejected");
            messageLabel.setTextFill(Color.RED);

        } else {

            messageLabel.setText("Transfer is Pending");
            messageLabel.setTextFill(Color.ORANGE);

        }
    }


    @FXML
    public void backOA(ActionEvent actionEvent) {

        playerNameTF.clear();
        fromClubTF.clear();
        toClubTF.clear();
        decisionCB.setValue(null);

        messageLabel.setText("Fields Cleared");
        messageLabel.setTextFill(Color.BLUE);
    }
}