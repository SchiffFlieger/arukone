package de.dhbw.arukone.gui;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.gui.controller.SolverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by karsten on 24.02.16.
 */
public class SolverGUI {

    public SolverGUI(ArukoneBoard board, int tileSize) {
        VBox root = null;
        SolverController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/solver.fxml"));
            root = loader.load();
            controller = loader.getController();
            controller.init(board.getSize(), board.getSize(), tileSize);
            controller.setBoard(board);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("de/dhbw/arukone/gui/css/style.css");

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Arukone Solver -- solving");
        stage.setScene(scene);
        stage.show();
    }
}
