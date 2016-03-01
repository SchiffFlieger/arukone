package de.dhbw.arukone.gui.init;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.gui.solver.SolverView;
import de.dhbw.arukone.reader.BoardReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * created by Karsten Köhler on 18.02.2016
 */
public class InitController implements Initializable {
    public TitledPane paneOptions;
    public ComboBox chooseBoard;
    public TextField txtSleepTime;
    public TextField txtTileSize;
    public Slider sliderSleepTime;
    public Slider sliderTileSize;
    public Button btnStart;
    public Button btnHelp;
    public Button btnAbout;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        fillComboBoxWithBoards();
        setNumericInputOnly(txtSleepTime, 0, 3000);
        setNumericInputOnly(txtTileSize, 0, 100);
        bindSleepTimeSlider();
        bindTileSizeSlider();
    }

    public void handleStart (ActionEvent actionEvent) {
        System.out.println(" -- start");
        ArukoneBoard board = (ArukoneBoard) chooseBoard.getSelectionModel().getSelectedItem();

        new SolverView(board, Integer.parseInt(txtTileSize.textProperty().get()), Integer.parseInt(txtSleepTime.textProperty().get()));
    }

    public void handleHelp (ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("content dummy");
        alert.showAndWait();
    }

    public void handleAbout (ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("content dummy");
        alert.showAndWait();
    }

    private void bindSleepTimeSlider() {
        txtSleepTime.textProperty().bindBidirectional(sliderSleepTime.valueProperty(), new NumberStringConverter("####"));
    }

    private void bindTileSizeSlider() {
        txtTileSize.textProperty().bindBidirectional(sliderTileSize.valueProperty(), new NumberStringConverter("###"));
    }

    private void fillComboBoxWithBoards() {
        ObservableList<ArukoneBoard> list = new BoardReader().readAllBoardsInDirectory("res/boards");
        list.sort((o1, o2) -> {
            if (o1.getSize() > o2.getSize()) {
                return 1;
            } else if (o1.getSize() < o2.getSize()) {
                return -1;
            } else {
                return o1.toString().compareTo(o2.toString());
            }
        });
        chooseBoard.setItems(list);
        if (!list.isEmpty()) {
            chooseBoard.setValue(list.get(0));
        }
    }

    private void setNumericInputOnly(TextField field, int minValue, int maxValue) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("[0-9]*")) {
                    if (newValue.equals("")) {
                        field.textProperty().setValue("0");
                        return;
                    }
                    int value = Integer.parseInt(newValue);
                    if (value < minValue) {
                        field.textProperty().setValue(String.valueOf(minValue));
                    } else if (value > maxValue) {
                        field.textProperty().setValue(String.valueOf(maxValue));
                    } else {
                        field.textProperty().setValue(String.valueOf(value));
                    }
                } else {
                    field.textProperty().setValue(oldValue);
                }
            }
        });
    }
}
