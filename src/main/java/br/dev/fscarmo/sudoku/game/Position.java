package br.dev.fscarmo.sudoku.game;


public final class Position {


    private final int gridRow;
    private final int gridCol;
    private final int number;
    private int guess;


    public Position(int gridRow, int gridCol, int number) {
        this.gridRow = gridRow;
        this.gridCol = gridCol;
        this.number = number;
    }


    public int getGridRow() {
        return gridRow;
    }


    public int getGridCol() {
        return gridCol;
    }


    public int getNumber() {
        return number;
    }


    public void lock() {
        guess = number;
    }


    public void guessTheNumber(int guess) throws IllegalArgumentException {
        if (isSelected())
            return;
        if (guess != number) {
            throw new IllegalArgumentException("Palpite incorreto!");
        }
        this.guess = guess;
    }


    public boolean isSelected() {
        return number == guess;
    }
}
