package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.Launcher;
import br.dev.fscarmo.sudoku.game.Game;

import br.dev.fscarmo.sudoku.game.Position;
import br.dev.fscarmo.sudoku.ui.Popup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SceneController implements Initializable {


    @FXML
    private GridPane gridNumbers;


    private final Game game = Game.getInstance();
    private final PositionController[][] positionControllers
            = new PositionController[game.getSizeFromGrid()][game.getSizeFromGrid()];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game.initialize();
        createConstraints();
        loadPositions();
        refresh();
    }


    private void createConstraints() {
        for (int i = 0; i < game.getSizeFromGrid(); i++) {
            RowConstraints rowConstraints = new RowConstraints(48);
            ColumnConstraints colConstraints = new ColumnConstraints(48);
            gridNumbers.getRowConstraints().add(rowConstraints);
            gridNumbers.getColumnConstraints().add(colConstraints);
        }
    }


    private void loadPositions() {
        int gridSize = game.getSizeFromGrid();
        try {
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("position.fxml"));
                    Node node = loader.load();
                    positionControllers[row][col] = loader.getController();
                    gridNumbers.add(node, col, row);
                }
            }
        } catch (IOException e) {
            Popup.open(Alert.AlertType.ERROR)
                    .show("Erro", e.getMessage());
        }
    }


    private void refresh() {
        int gridSize = game.getSizeFromGrid();
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Position position = game.getPositionFromGrid(row, col);
                positionControllers[row][col].setPosition(position);
            }
        }
    }
}
