package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

final class TwoSumTest {

    @ParameterizedTest
    @MethodSource("twoSumData")
    void twoSumTest(final int[] nums, final int target, final int[] expected) {

        final int[] actual = TwoSum.twoSum(nums, target);

        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> twoSumData() {

        return Stream.of(
                Arguments.of(new int[]{}, 10, new int[]{}),
                Arguments.of(new int[]{4}, 10, new int[]{}),
                Arguments.of(new int[]{5}, 10, new int[]{}),
                Arguments.of(new int[]{5, 5}, 5, new int[]{}),
                Arguments.of(new int[]{3, 4, 5, 6}, 7, new int[]{0, 1}),
                Arguments.of(new int[]{4, 5, 6}, 10, new int[]{0, 2}),
                Arguments.of(new int[]{5, 5}, 10, new int[]{0, 1})
        );
    }
}
