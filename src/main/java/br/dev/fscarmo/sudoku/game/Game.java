package br.dev.fscarmo.sudoku.game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Duration;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Game {
    private static volatile Game INSTANCE;


    public static Game current() {
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
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final SimpleStringProperty errors = new SimpleStringProperty("0");
    private final SimpleStringProperty time = new  SimpleStringProperty("00:00:00");


    int seconds = 0;
    private final Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), _-> {
                seconds++;

                int h = seconds / 3600;
                int m = (seconds % 3600) / 60;
                int s = seconds % 60;

                time.set(String.format("%02d:%02d:%02d", h, m, s));
            })
    );


    private Game() {}


    public void setState(State state) {
        support.firePropertyChange("state", this.state, state);
        this.state = state;
    }


    public Space getBoardSpace(final int row, final int col) {
        return board.getSpace(row, col);
    }


    public int getBoardSize() {
        return Board.BOARD_SIZE;
    }


    public SimpleStringProperty getErrors() {
        return errors;
    }


    public SimpleStringProperty getTime() {
        return time;
    }


    public boolean isRunning() {
        return state == State.RUNNING;
    }


    public boolean isPaused() {
        return state == State.PAUSED;
    }


    public void loadGame() {
        board = new Board();
        seconds = 0;

        board.loadBoard();
        errors.set("0");
        time.set("00:00:00");
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();

        setState(State.PAUSED);
    }


    public void play() {
        if (state == State.PAUSED) {
            setState(State.RUNNING);
            timeline.play();
        }
    }


    public void pause() {
        if  (state == State.RUNNING) {
            setState(State.PAUSED);
            timeline.stop();
        }
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
            timeline.stop();
            setState(State.FINISHED);
        } else if (errors.get().equals("10")) {
            timeline.stop();
            setState(State.GAME_OVER);
        }
    }
}
