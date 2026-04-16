package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.Launcher;
import br.dev.fscarmo.sudoku.game.Game;
import br.dev.fscarmo.sudoku.game.Space;
import br.dev.fscarmo.sudoku.game.State;
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
    private GridPane board;

    private final Game game = Game.currentGame();
    private SpaceController[][] controllers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();

        game.addStateListener(event -> {
            var state = (State) event.getNewValue();
            if (state != State.RUNNING) {
                load();
            }
        });
    }


    private void load() {
        game.loadGame();

        controllers = new SpaceController[game.getBoardSize()][game.getBoardSize()];

        loadBoard();
        setupBoardSpaces();
    }


    private void loadBoard() {
        board.getChildren().clear();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                GridPane block = loadSpaceControllers(r, c);
                board.add(block, c, r);
            }
        }
    }


    private GridPane loadSpaceControllers(final int rowBlock, final int colBlock) {
        GridPane block = Grid.createNewBlock(3);

        try {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("space.fxml"));
                    Node node = loader.load();
                    SpaceController controller = loader.getController();

                    int rowIndex = rowBlock * 3 + r;
                    int colIndex = colBlock * 3 + c;
                    controllers[rowIndex][colIndex] = controller;

                    block.add(node, c, r);
                }
            }
        } catch (IOException e) {
            Popup.error().show("Erro", e.getMessage());
        }

        return block;
    }


    private void setupBoardSpaces() {
        int boardSize = game.getBoardSize();

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Space space = game.getBoardSpace(r, c);

                controllers[r][c].setSpace(space);
                controllers[r][c].load();
            }
        }
    }
}
