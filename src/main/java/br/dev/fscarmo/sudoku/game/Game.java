package br.dev.fscarmo.sudoku.game;


import br.dev.fscarmo.sudoku.ui.Popup;

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
    private State state;
    private short errors;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    private Game() {}


    public Space getBoardSpace(int row, int col) {
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
        errors = 0;
        state = State.RUNNING;
        board = new Board();
        board.initialize();
    }


    public void addStateListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener("state", listener);
    }


    public void increaseErrors() {
        errors++;
    }


    public void checkStatus() {
        if (board.isCompletelySelected()) {
            setState(State.FINISHED);
            Popup.info().show("Parabéns!", "Você acertou todos os números!!!");
            loadGame();
        } else if (errors > 10) {
            setState(State.GAME_OVER);
            Popup.warning().show("Wops!", "Você ultrapassou o limite de 10 erros. O jogo será reiniciado!");
            loadGame();
        }
    }
}
