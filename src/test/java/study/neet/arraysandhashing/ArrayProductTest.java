package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

final class ArrayProductTest {

    @ParameterizedTest
    @MethodSource("isArrayProductData")
    void isAnagramTest(final int[] values, final int[] expected) {

        final int[] actual = ArrayProduct.productExceptSelf(values);


        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> isArrayProductData() {

        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 4, 6},
                        new int[]{48, 24, 12, 8}
                ),
                Arguments.of(
                        new int[]{-1, 0, 1, 2, 3},
                        new int[]{0, -6, 0, 0, 0}
                )
        );
    }
}
