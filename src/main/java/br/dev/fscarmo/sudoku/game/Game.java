package br.dev.fscarmo.sudoku.game;


import br.dev.fscarmo.sudoku.ui.Popup;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Game {
    private static volatile Game INSTANCE;


    public static Game getInstance() {
        if (INSTANCE == null) {
            synchronized (Game.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Game();
                }
            }
        }
        return INSTANCE;
    }


    private StringProperty errors;
    private Grid grid;


    private Game() {
        initialize();
    }


    public void initialize() {
        errors = new SimpleStringProperty("0");
        grid = new Grid();
        grid.initialize();
    }


    public Space getGridSpace(int row, int col) {
        return grid.getPosition(row, col);
    }


    public int getGridSize() {
        return Grid.SIZE;
    }


    public void increaseErrors() {
        int numericErrors = Integer.parseInt(errors.get());
        errors.set(String.valueOf(numericErrors + 1));
    }


    public void checkStatus() {
        if (grid.isCompletelySelected()) {
            Popup.info().show("Parabéns!", "Você acertou todos os números!!!");
            initialize();
        } else if (errors.get().equals("10")) {
            Popup.warning().show("Wops!", "Você atingiu o limite máximo de 10 erros. O jogo será reiniciado!");
            initialize();
        }
    }
}
