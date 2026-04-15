package br.dev.fscarmo.sudoku.game;


import br.dev.fscarmo.sudoku.ui.Popup;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Game {
    private static volatile Game INSTANCE;


    public static Game currentGame() {
        if (INSTANCE == null) {
            synchronized (Game.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Game();
                }
            }
        }
        return INSTANCE;
    }


    private Board board;
    private StringProperty errors;
    private final PropertyChangeSupport state = new PropertyChangeSupport(this);


    private Game() {}


    public Space getBoardSpace(int row, int col) {
        return board.getSpace(row, col);
    }


    public int getBoardSize() {
        return Board.BOARD_SIZE;
    }


    public void loadGame() {
        errors = new SimpleStringProperty("0");
        board = new Board();

        state.firePropertyChange("board", null, board);
        board.initialize();
    }


    public void addStateListener(PropertyChangeListener listener) {
        state.addPropertyChangeListener("board", listener);
    }


    public void increaseErrors() {
        int numericErrors = Integer.parseInt(errors.get());
        errors.set(String.valueOf(numericErrors + 1));
    }


    public void checkStatus() {
        if (board.isCompletelySelected()) {
            Popup.info().show("Parabéns!", "Você acertou todos os números!!!");
            loadGame();
        } else if (errors.get().equals("10")) {
            Popup.warning().show("Wops!", "Você atingiu o limite máximo de 10 erros. O jogo será reiniciado!");
            loadGame();
        }
    }
}
