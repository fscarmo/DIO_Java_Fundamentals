package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.Launcher;
import br.dev.fscarmo.sudoku.game.Board;
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


    private final Game game = Game.current();


    private SpaceController[][] controllers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelErrors.textProperty().bind(game.getErrors());
        labelTime.textProperty().bind(game.getTime());

        game.addStateListener(event -> {
            var newState = (State) event.getNewValue();
            handleGameStateChange(newState);
        });

        loadScene();
    }


    @FXML
    private void loadScene() {
        game.loadGame();

        controllers = new SpaceController[game.getBoardSize()][game.getBoardSize()];

        loadBoard();
        setupBoardSpaces();
    }


    @FXML
    private void togglePlayAndPause() {
        if (game.isPaused()) {
            game.play();
        } else {
            game.pause();
        }
    }


    private void loadBoard() {
        short blockSize = Board.BLOCK_SIZE;

        board.getChildren().clear();

        for (int r = 0; r < blockSize; r++) {
            for (int c = 0; c < blockSize; c++) {
                GridPane block = loadSpaceControllers(r, c);
                board.add(block, c, r);
            }
        }
    }


    private GridPane loadSpaceControllers(final int rowBlock, final int colBlock) {
        short blockSize = Board.BLOCK_SIZE;
        GridPane block = Grid.createNewBlock(3);

        try {
            for (int r = 0; r < blockSize; r++) {
                for (int c = 0; c < blockSize; c++) {
                    FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("space.fxml"));
                    Node node = loader.load();
                    SpaceController controller = loader.getController();

                    int rowIndex = rowBlock * blockSize + r;
                    int colIndex = colBlock * blockSize + c;
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


    private void handleGameStateChange(State newState) {
        switch (newState) {
            case RUNNING:
                btnPause.setDisable(false);
                btnPlay.setDisable(true);
                showBoardSpaces();
                break;

            case PAUSED:
                btnPause.setDisable(true);
                btnPlay.setDisable(false);
                hideBoardSpaces();
                break;

            case FINISHED:
                Popup.confirm(this::loadScene)
                        .show("Ganhou!", "Você acertou todos os números. O jogo será reiniciado!");
                break;

            default:
                Popup.confirm(this::loadScene)
                        .show("Perdeu!", "Você cometeu 10 erros. O jogo será reiniciado!");
                break;
        }
    }


    private void hideBoardSpaces() {
        if (controllers != null) {
            Arrays.stream(controllers).forEach(c -> Arrays.stream(c).forEach(SpaceController::lock));
        }
    }


    private void showBoardSpaces() {
        if (controllers != null) {
            Arrays.stream(controllers).forEach(c -> Arrays.stream(c).forEach(SpaceController::unlock));
        }
    }
}
