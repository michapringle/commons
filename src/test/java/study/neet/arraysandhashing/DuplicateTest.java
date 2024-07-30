package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class DuplicateTest {

    @ParameterizedTest
    @MethodSource("hasDuplicateData")
    void hasDuplicateTest(final int[] nums, final boolean expected) {

        final boolean actual = Duplicate.hasDuplicate(nums);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> hasDuplicateData() {

        return Stream.of(
                Arguments.of(new int[]{}, false),
                Arguments.of(new int[]{1}, false),
                Arguments.of(new int[]{1, 2, 3, 3}, true),
                Arguments.of(new int[]{1, 2, 3, 4}, false),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 3}, true)
        );
    }
}
