package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class AnagramTest {

    @ParameterizedTest
    @MethodSource("isAnagramData")
    void isAnagramTest(final String string1, final String string2, final boolean expected) {

        final boolean actual = Anagram.isAnagram(string1, string2);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("isGroupAnagramData")
    void isGroupAnagramTest(final String[] strings, final List<List<String>> expected) {

        final List<List<String>> actual = Anagram.groupAnagrams(strings);

        final Set<List<String>> actualSet = new HashSet<>();

        for (final List<String> single : actual) {
            Collections.sort(single);
            actualSet.add(single);
        }

        for (final List<String> single : expected) {
            Collections.sort(single);
            assertTrue(actualSet.contains(single));
        }
    }

    static Stream<Arguments> isAnagramData() {

        return Stream.of(
                Arguments.of("", "", true),
                Arguments.of("a", "a", true),
                Arguments.of("a", "b", false),
                Arguments.of("ab", "ba", true),
                Arguments.of("ab", "ab", true)
        );
    }

    static Stream<Arguments> isGroupAnagramData() {

        return Stream.of(
                Arguments.of(
                        new String[]{""},
                        newList(
                                newList("")
                        )
                ),
                Arguments.of(
                        new String[]{"x"},
                        newList(
                                newList("x")
                        )
                ),
                Arguments.of(
                        new String[]{"act", "pots", "tops", "cat", "stop", "hat"},
                        newList(
                                newList("hat"),
                                newList("act", "cat"),
                                newList("stop", "pots", "tops")
                        )
                )
        );
    }

    private static <T> List<T> newList(final T... ts) {
        return Arrays.stream(ts).collect(Collectors.toList());
    }
}
