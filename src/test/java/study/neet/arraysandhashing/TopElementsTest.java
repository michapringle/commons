package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

final class TopElementsTest {

    @ParameterizedTest
    @MethodSource("isTopKFrequentData")
    void isTopKFrequentTest(final int[] nums, final int k, final int[] expected) {

        final int[] actual = TopElements.topKFrequent(nums, k);
        Arrays.sort(actual);
        Arrays.sort(expected);

        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> isTopKFrequentData() {

        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 3, 3, 3}, 2, new int[]{2, 3}),
                Arguments.of(new int[]{7, 7}, 1, new int[]{7}),
                Arguments.of(new int[]{1, 2}, 2, new int[]{1, 2})
        );
    }
}
