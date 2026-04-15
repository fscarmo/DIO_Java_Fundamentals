package br.dev.fscarmo.sudoku.game;


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


    private final Grid grid;


    private Game() {
        grid = new Grid();
    }


    public void initialize() {
        grid.initialize();
    }


    public Space getGridSpace(int row, int col) {
        return grid.getPosition(row, col);
    }


    public int getGridSize() {
        return Grid.SIZE;
    }


    public boolean isFinished() {
        return grid.isCompletelySelected();
    }
}
