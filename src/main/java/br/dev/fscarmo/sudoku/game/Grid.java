package br.dev.fscarmo.sudoku.game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class Grid {
    public static final short SIZE = 9;


    private static List<Integer> generateShuffleNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++)
            numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

    private static List<Integer> generateShuffledIndexes() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            indexes.add(i);
        Collections.shuffle(indexes);
        return indexes.subList(0, 3);
    }


    private final int sqrt;
    private final Space[][] spaces;


    public Grid() {
        this.sqrt = (int) Math.sqrt(SIZE);
        this.spaces = new Space[SIZE][SIZE];
    }


    public void initialize() {
        List<Integer> shuffledNumbers = generateShuffleNumbers();

        for (int rowBlock = 0; rowBlock < 3; rowBlock++) {
            for (int colBlock = 0; colBlock < 3; colBlock++) {
                List<Integer> shuffledIndexes = generateShuffledIndexes();

                for (int i = 0; i < 9; i++) {
                    int row = (rowBlock * 3) + (i / 3);
                    int col = (colBlock * 3) + (i % 3);
                    int idx = (row * sqrt + row / sqrt + col) % SIZE;
                    var pos = new Space(row, col, shuffledNumbers.get(idx));

                    if (shuffledIndexes.contains(i))
                        pos.lock();

                    this.spaces[row][col] = pos;
                }
            }
        }
    }


    public Space getPosition(final int row, final int col) {
        return spaces[row][col];
    }


    public boolean isCompletelySelected() {
        int selectedCount = 0;
        for (var rows : spaces) {
            selectedCount += (int) Arrays.stream(rows)
                    .filter(Space::isSelected)
                    .count();
        }
        return selectedCount == SIZE * SIZE;
    }
}
