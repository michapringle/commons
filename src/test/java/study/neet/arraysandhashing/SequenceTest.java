package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class SequenceTest {

    @ParameterizedTest
    @MethodSource("isLongestSequenceData")
    void isLongestSequenceTest(final int[] sequence, final int expected) {

        final int actual = Sequence.longestConsecutive(sequence);

        assertEquals(expected, actual);
    }


    static Stream<Arguments> isLongestSequenceData() {

        return Stream.of(
                Arguments.of(new int[]{0, -1}, 2),
                Arguments.of(new int[]{1, 2}, 2),
                Arguments.of(new int[]{}, 0),
                Arguments.of(new int[]{0}, 1),
                Arguments.of(new int[]{0, 0}, 1),
                Arguments.of(new int[]{2, 3, 10, 9, 11, 4, 5, 6}, 5),
                Arguments.of(new int[]{2, 20, 4, 10, 3, 4, 5}, 4),
                Arguments.of(new int[]{0, 3, 2, 5, 4, 6, 1, 1}, 7),
                Arguments.of(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}, 7)
        );
    }
}
