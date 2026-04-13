package br.dev.fscarmo.sudoku.game;


public final class Position {


    private final int number;
    private int guess;


    public Position(int number) {
        this.number = number;
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
            throw new IllegalArgumentException();
        }
        this.guess = guess;
    }


    public boolean isSelected() {
        return number == guess;
    }
}
