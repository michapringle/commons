package study.neet.arraysandhashing;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class Sudoku {

    private Sudoku() {
    }

    /**
     * You are given a 9 x 9 Sudoku board. A Sudoku board is valid if the following
     * rules are followed:
     * <p>
     * Each row must contain the digits 1-9 without duplicates.
     * Each column must contain the digits 1-9 without duplicates.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
     * without duplicates.
     * <p>
     * Return true if the Sudoku board is valid, otherwise return false
     * <p>
     * Note: A board does not need to be full or be solvable to be valid.
     */
    // 2:48 -> 5:57 - the stupid 3x3 formula proved tricky
    static boolean isValidSudoku(final char[][] board) {

        for (final char[] row : board) {
            if (!isValidGroup(row)) {
                return false;
            }
        }

        for (final char[] column : transposeRowsAndColumns(board)) {
            if (!isValidGroup(column)) {
                return false;
            }
        }

        for (final char[] subBox : transposeToSubBox(board)) {
            if (!isValidGroup(subBox)) {
                return false;
            }
        }

        return true;
    }

    private static char[][] transposeRowsAndColumns(char[][] board) {

        final char[][] columns = new char[board.length][board[0].length];
        int col = 0;
        for (final char[] row : board) {
            for (int i = 0; i < 9; i++) {
                columns[i][col] = row[i];
            }
            col++;
        }
        return columns;
    }

    private static char[][] transposeToSubBox(final char[][] board) {

        final char[][] subBox = new char[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {

                final int boxNumber = (row / 3) * 3 + (column / 3);
                final int boxIndex = (row % 3) * 3 + column % 3;
                subBox[boxNumber][boxIndex] = board[row][column];
            }
        }

        return subBox;
    }

    private static boolean isValidGroup(final char[] group) {

        final Set<Character> uniqueCharacters = new HashSet<>();
        final List<Character> allCharacters = new ArrayList<>();

        for (final char box : group) {
            if (box == '.') {
                continue;
            }
            uniqueCharacters.add(box);
            allCharacters.add(box);
        }

        return uniqueCharacters.size() == allCharacters.size();
    }
}
