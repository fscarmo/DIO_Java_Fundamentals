package br.dev.fscarmo.sudoku.game;


import br.dev.fscarmo.sudoku.ui.Popup;

import javafx.beans.property.SimpleStringProperty;

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


    private State state;
    private Board board;
    private final SimpleStringProperty errors = new SimpleStringProperty("0");
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    private Game() {}


    public Space getBoardSpace(final int row, final int col) {
        return board.getSpace(row, col);
    }


    public int getBoardSize() {
        return Board.BOARD_SIZE;
    }


    public void setState(State state) {
        support.firePropertyChange("state", this.state, state);
        this.state = state;
    }


    public void loadGame() {
        state = State.RUNNING;
        board = new Board();
        board.loadBoard();
        errors.set("0");
    }


    public void addStateListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener("state", listener);
    }


    public void increaseErrors() {
        int errors = Integer.parseInt(this.errors.get());
        this.errors.set(Integer.toString(errors + 1));
    }


    public void checkStatus() {
        if (board.isCompletelySelected()) {
            setState(State.FINISHED);
            Popup.info().show("Parabéns!", "Você acertou todos os números!!!");
        } else if (errors.get().equals("10")) {
            setState(State.GAME_OVER);
            Popup.warning().show("Wops!", "Você atingiu o limite de 10 erros. Tente novamente!");
        }
    }
}
