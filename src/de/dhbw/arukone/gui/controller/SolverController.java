package de.dhbw.arukone.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by karsten on 24.02.16.
 */
public class SolverController implements Initializable {
    public Button btnPause;
    public Button btnResume;
    public Button btnStop;
    public Pane boardPane;

    private Map<String, StackPane> tileMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileMap = new HashMap<>();
    }

    public void init(int width, int height, int tileSize) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(tileSize, tileSize);
                cell.setLayoutX(x*tileSize);
                cell.setLayoutY(y*tileSize);
                cell.getStyleClass().add("empty");

                boardPane.getChildren().add(cell);
                tileMap.put(String.format("[%d,%d]", x, y), cell);
            }
        }
    }

    public void handlePause(ActionEvent actionEvent) {

    }

    public void handleResume(ActionEvent actionEvent) {

    }

    public void handleStop(ActionEvent actionEvent) {

    }
}
