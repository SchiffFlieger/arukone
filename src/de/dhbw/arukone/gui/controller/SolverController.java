package de.dhbw.arukone.gui.controller;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.ArukoneSolver;
import de.dhbw.arukone.Path;
import de.dhbw.arukone.Point;
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
    public Button btnContinue;
    public Pane boardPane;

    private ArukoneBoard board;
    private ArukoneSolver solver;
    private Map<String, StackPane> tileMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileMap = new HashMap<>();
        solver = new ArukoneSolver();
    }

    public void init(int width, int height, int tileSize) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(tileSize, tileSize);
                cell.setLayoutX(x*tileSize);
                cell.setLayoutY(y*tileSize);
                cell.getStyleClass().add("root");

                boardPane.getChildren().add(cell);
                tileMap.put(String.format("[%d,%d]", x, y), cell);
            }
        }
    }

    public void setBoard(ArukoneBoard board) {
        this.board = board;
        drawBoard();
    }

    public void handlePause(ActionEvent actionEvent) {
        System.out.println("pause");
    }

    public void handleContinue(ActionEvent actionEvent) {
        System.out.println("continue");
        solver.solve(board, 1);
    }

    public void drawBoard() {
        for (Path path : board.getPaths()) {
            for (Point point : path.getAllPoints()) {
                drawPoint(point, path.getId());
            }
        }
    }

    private void drawPoint(Point point, int pathId) {
        tileMap.get(String.format("[%d,%d]", point.getX(), point.getY())).getStyleClass().clear();
        tileMap.get(String.format("[%d,%d]", point.getX(), point.getY())).getStyleClass().add("root");
        tileMap.get(String.format("[%d,%d]", point.getX(), point.getY())).getStyleClass().add("filled" + pathId);
    }
}
