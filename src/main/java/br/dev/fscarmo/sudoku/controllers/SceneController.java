package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.Launcher;
import br.dev.fscarmo.sudoku.game.Game;
import br.dev.fscarmo.sudoku.game.Space;
import br.dev.fscarmo.sudoku.ui.Grid;
import br.dev.fscarmo.sudoku.ui.Popup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SceneController implements Initializable {


    @FXML
    private GridPane mainGrid;


    private final Game game = Game.getInstance();
    private final SpaceController[][] spaces
            = new SpaceController[game.getGridSize()][game.getGridSize()];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game.initialize();
        loadGrid();
        setupGridSpaces();
    }


    private void loadGrid() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                GridPane block = loadControlledBlock(r, c);
                mainGrid.add(block, c, r);
            }
        }
    }


    private GridPane loadControlledBlock(final int rowBlock, final int colBlock) {
        GridPane block = Grid.createNewBlock(3);

        try {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("space.fxml"));
                    Node node = loader.load();
                    SpaceController controller = loader.getController();

                    // Essencial para embaralhar os espaços dentro de cada bloco 3x3, sem permitir que isso gere
                    // números repetidos nas linhas ou colunas dos outros blocos.
                    int rowIndex = rowBlock * 3 + r;
                    int colIndex = colBlock * 3 + c;
                    spaces[rowIndex][colIndex] = controller;

                    block.add(node, c, r);
                }
            }
        } catch (IOException e) {
            Popup.error().show("Erro", e.getMessage());
        }

        return block;
    }


    private void setupGridSpaces() {
        int gridSize = game.getGridSize();

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                Space space = game.getGridSpace(r, c);

                spaces[r][c].setSpace(space);
                spaces[r][c].refresh();
            }
        }
    }
}
