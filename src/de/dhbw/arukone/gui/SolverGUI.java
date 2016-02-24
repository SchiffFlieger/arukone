package de.dhbw.arukone.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karsten on 24.02.16.
 */
public class SolverGUI {

    private final int WIDTH;
    private final int HEIGHT;
    private final int TILE_SIZE;

    private Map<String, StackPane> tileMap;
    private Scene scene;
    private Stage stage;

    public SolverGUI(int width, int height, int tileSize) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TILE_SIZE = tileSize;
        this.tileMap = new HashMap<>();

        Pane root = new Pane();
        scene = new Scene(root, TILE_SIZE, TILE_SIZE);
        scene.getStylesheets().add("de/dhbw/arukone/gui/css/style.css");


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(TILE_SIZE, TILE_SIZE);
                cell.setLayoutX(x*TILE_SIZE);
                cell.setLayoutY(y*TILE_SIZE);
                cell.getStyleClass().add("empty");

                root.getChildren().add(cell);
                tileMap.put(String.format("[%d,%d]", x, y), cell);
            }
        }

        stage = new Stage();
        stage.setTitle("Arukone Solver -- solving");
        stage.setScene(scene);
        stage.setWidth(5+WIDTH*TILE_SIZE);
        stage.setHeight(40+HEIGHT*TILE_SIZE);
        stage.show();
    }





}
