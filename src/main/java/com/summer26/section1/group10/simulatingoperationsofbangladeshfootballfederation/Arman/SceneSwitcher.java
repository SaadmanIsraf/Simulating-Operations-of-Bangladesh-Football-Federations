package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static Stage stage;

    public static void switchTo(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFileName));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

