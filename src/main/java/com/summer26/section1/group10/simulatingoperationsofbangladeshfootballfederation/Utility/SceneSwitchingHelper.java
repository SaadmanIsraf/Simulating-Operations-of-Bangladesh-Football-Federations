package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitchingHelper {

    /** Switches the current window to a new FXML scene with no data passed. */
    public static void fullSceneReplacement(ActionEvent event, String fxml) {
        if (fxml == null || fxml.trim().isEmpty()) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitchingHelper.class.getResource(fxml.trim()));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML: " + fxml, e);
        }
    }

    /**
     * Switches to a new FXML scene and passes {@code data} to the destination
     * controller, if that controller implements {@link DataReceiver}.
     * Example: SceneSwitchingHelper.switchSceneWithData(event, "PlayerProfileView.fxml", selectedPlayer);
     */
    public static <T> void switchSceneWithData(ActionEvent event, String fxml, T data) {
        if (fxml == null || fxml.trim().isEmpty()) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitchingHelper.class.getResource(fxml.trim()));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof DataReceiver) {
                @SuppressWarnings("unchecked")
                DataReceiver<T> receiver = (DataReceiver<T>) controller;
                receiver.receiveData(data);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML: " + fxml, e);
        }
    }
}