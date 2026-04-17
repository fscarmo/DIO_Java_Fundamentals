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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class SceneController implements Initializable {


    @FXML
    private GridPane board;

    @FXML
    private Label labelErrors;

    @FXML
    private Label labelTime;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPause;


    private final Game currentGame = Game.currentGame();
    private SpaceController[][] controllers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelErrors.textProperty().bind(currentGame.getErrors());
        labelTime.textProperty().bind(currentGame.getTime());

        currentGame.addStateListener(event -> {
            var newState = (State) event.getNewValue();
            if (newState.equals(State.RUNNING)) {
                btnPause.setDisable(false);
                btnPlay.setDisable(true);
                showBoardSpaces();
            } else if (newState.equals(State.PAUSED)) {
                btnPause.setDisable(true);
                btnPlay.setDisable(false);
                hideBoardSpaces();
            } else {
                loadScene();
            }
        });

        loadScene();
    }


    @FXML
    private void loadScene() {
        currentGame.loadGame();

        controllers = new SpaceController[currentGame.getBoardSize()][currentGame.getBoardSize()];

        loadBoard();
        setupBoardSpaces();
    }


    @FXML
    private void togglePlayAndPause() {
        if (currentGame.isPaused()) {
            currentGame.play();
        } else {
            currentGame.pause();
        }
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
        int boardSize = currentGame.getBoardSize();

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                Space space = currentGame.getBoardSpace(r, c);

                controllers[r][c].setSpace(space);
                controllers[r][c].load();
            }
        }
    }


    private void hideBoardSpaces() {
        if (controllers != null) {
            Arrays.stream(controllers).forEach(controller ->
                    Arrays.stream(controller).forEach(SpaceController::lock));
        }
    }


    private  void showBoardSpaces() {
        if (controllers != null) {
            Arrays.stream(controllers).forEach(controller ->
                    Arrays.stream(controller).forEach(SpaceController::unlock));
        }
    }
}
