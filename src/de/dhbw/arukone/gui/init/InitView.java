package de.dhbw.arukone.gui.init;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * created by Karsten Köhler on 18.02.2016
 */
public class InitView extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setTitle("Arukone Solver");

        VBox pane = FXMLLoader.load(getClass().getResource("../fxml/init.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
