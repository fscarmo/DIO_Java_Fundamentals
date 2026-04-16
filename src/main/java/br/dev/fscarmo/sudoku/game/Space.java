package br.dev.fscarmo.sudoku.game;


public final class Space {


    private final int number;
    private int guess;


    public Space(int number) {
        this.number = number;
    }


    public int getNumber() {
        return number;
    }


    public void lock() {
        guess = number;
    }


    public void guessTheNumber(int guess) throws IllegalArgumentException {
        if (isSelected()) {
            return;
        }
        if (guess != number) {
            throw new IllegalArgumentException(
                    String.format("O número %d não pertence a este espaço!", guess));
        }
        this.guess = guess;
    }


    public boolean isSelected() {
        return number == guess;
    }
}
