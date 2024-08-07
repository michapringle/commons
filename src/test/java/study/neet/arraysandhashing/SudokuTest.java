package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class SudokuTest {

    @ParameterizedTest
    @MethodSource("isValidSudokuData")
    void isValidSudokuTest(final char[][] sudoku, final boolean expected) {

        final boolean actual = Sudoku.isValidSudoku(sudoku);

        assertEquals(expected, actual);
    }


    static Stream<Arguments> isValidSudokuData() {

        return Stream.of(
                Arguments.of(
                        new char[][]{
                                {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                                {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                                {'.', '9', '8', '.', '.', '.', '.', '.', '3'},
                                {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                                {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                                {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                        },
                        true
                ),

                Arguments.of(
                        new char[][]{
                                {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                                {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                                {'.', '9', '1', '.', '.', '.', '.', '.', '3'},
                                {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                                {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                                {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                        },
                        false
                )

        );
    }

}
