package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneSwitcher {
    public static Stage stage;

    public static void switchTo(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFileName));
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void switchSceneWithData(javafx.event.ActionEvent event, String fxml, User user) {
        try {

            URL location = SceneSwitcher.class.getResource(fxml);

            if (location == null) {
                AlertGenerator.showAlert(
                        "FXML Error",
                        "FXML file not found:\n" + fxml
                );
                return;
            }

            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof UserReceiver receiver) {
                receiver.setLoggedInUser(user);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            AlertGenerator.showAlert(
                    "Scene Loading Error",
                    "Unable to load the FXML file."
            );

        } catch (Exception e) {
            e.printStackTrace();
            AlertGenerator.showAlert(
                    "Unexpected Error",
                    e.getMessage()
            );
        }
    }
}