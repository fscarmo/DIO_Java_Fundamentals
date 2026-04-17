package br.dev.fscarmo.sudoku.game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class Board {
    public static final short BOARD_SIZE = 9;
    public static final short BLOCK_SIZE = (short) Math.sqrt(BOARD_SIZE);


    private static List<Integer> generateShuffleNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }


    private static List<Integer> generateShuffledIndexes() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        return indexes.subList(0, 3);
    }


    private Space[][] spaces;
    private List<Integer> shuffledNumbers;


    public Space getSpace(final int row, final int col) {
        return spaces[row][col];
    }


    public boolean isCompletelySelected() {
        int selectedCount = 0;
        for (var rows : spaces) {
            selectedCount += (int) Arrays.stream(rows)
                    .filter(Space::isSelected)
                    .count();
        }
        return selectedCount == BOARD_SIZE * BOARD_SIZE;
    }


    public void loadBoard() {
        spaces = new Space[BOARD_SIZE][BOARD_SIZE];
        shuffledNumbers = generateShuffleNumbers();

        for (int rowBlock = 0; rowBlock < BLOCK_SIZE; rowBlock++) {
            for (int colBlock = 0; colBlock < BLOCK_SIZE; colBlock++) {
                loadSpacePerBlock(rowBlock, colBlock);
            }
        }
    }


    private void loadSpacePerBlock(final int rowBlock, final int colBlock) {
        List<Integer> shuffledIndexes = generateShuffledIndexes();

        for (int i = 0; i < BOARD_SIZE; i++) {
            int row = (rowBlock * BLOCK_SIZE) + (i / BLOCK_SIZE);
            int col = (colBlock * BLOCK_SIZE) + (i % BLOCK_SIZE);
            int index = (row * BLOCK_SIZE + row / BLOCK_SIZE + col) % BOARD_SIZE;
            int number = shuffledNumbers.get(index);
            var space = new Space(number);

            if (shuffledIndexes.contains(i)) {
                space.fixNumber();
            }

            spaces[row][col] = space;
        }
    }
}
