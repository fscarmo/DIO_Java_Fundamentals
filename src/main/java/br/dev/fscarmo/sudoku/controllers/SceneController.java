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
    private GridPane mainGrid;


    private final Game game = Game.getInstance();
    private final PositionController[][] positionControllers
            = new PositionController[game.getSizeFromGrid()][game.getSizeFromGrid()];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game.initialize();
        loadTray();
        refresh();
    }


    private void loadTray() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                GridPane block = createTrayBlock(row, col);
                block.setStyle(
                        """
                        -fx-border-color: #334155;
                        -fx-border-width: 1;
                        -fx-padding: 3;
                        """
                );
                mainGrid.add(block, col, row);
            }
        }
    }

    private GridPane createTrayBlock(int rowBlock, int colBlock) {
        GridPane block = new GridPane();
        PositionController controller;
        FXMLLoader loader;
        Node node;

        block.setHgap(3);
        block.setVgap(3);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                try {
                    loader = new FXMLLoader(Launcher.class.getResource("position.fxml"));
                    node = loader.load();
                    controller = loader.getController();

                    int rowIndex = rowBlock * 3 + row;
                    int colIndex = colBlock * 3 + col;

                    positionControllers[rowIndex][colIndex] = controller;
                    block.add(node, col, row);
                } catch (IOException e) {
                    Popup.open(Alert.AlertType.ERROR)
                            .show("Erro", e.getMessage());
                }
            }
        }

        return block;
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
