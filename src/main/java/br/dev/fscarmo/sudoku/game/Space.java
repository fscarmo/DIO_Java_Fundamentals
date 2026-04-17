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


    public void fixNumber() {
        guess = number;
    }


    public void guessTheNumber(final int guess) throws IllegalArgumentException {
        if (isSelected()) {
            return;
        }
        if (guess < 1 || guess > 9) {
            throw new IllegalArgumentException(
                    String.format("O valor %d não é permitido. Escolha um número de 1 a 9!", guess));
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
