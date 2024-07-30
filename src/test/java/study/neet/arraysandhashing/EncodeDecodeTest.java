package study.neet.arraysandhashing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class EncodeDecodeTest {

    @ParameterizedTest
    @MethodSource("encodeData")
    void encodeTest(final List<String> input, final String expected) {

        final String actual = EncodeDecode.encode(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("decodeData")
    void decodeTest(final String input, final List<String> expected) {

        final List<String> actual = EncodeDecode.decode(input);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> encodeData() {

        return Stream.of(
                Arguments.of(
                        List.of(),
                        null
                ),
                Arguments.of(
                        List.of(""),
                        ","
                ),
                Arguments.of(
                        List.of(",,", ",,"),
                        "|,|,,|,|,,"
                ),
                Arguments.of(
                        List.of(",", ",", ",", ","),
                        "|,,|,,|,,|,,"
                ),
                Arguments.of(
                        List.of("||", "||"),
                        "||||,||||,"
                ),
                Arguments.of(
                        List.of("|", "|", "|", "|"),
                        "||,||,||,||,"
                ),
                Arguments.of(
                        List.of("neet", "code", "love", "you"),
                        "neet,code,love,you,"
                ),
                Arguments.of(
                        List.of("we", "say", ":", "yes"),
                        "we,say,:,yes,"
                )
        );
    }

    static Stream<Arguments> decodeData() {

        return Stream.of(
                Arguments.of(
                        null,
                        List.of()
                ),
                Arguments.of(
                        ",",
                        List.of("")
                ),
                Arguments.of(
                        "|,|,,|,|,,",
                        List.of(",,", ",,")
                ),
                Arguments.of(
                        "|,,|,,|,,|,,",
                        List.of(",", ",", ",", ",")
                ),
                Arguments.of(
                        "||||,||||,",
                        List.of("||", "||")
                ),
                Arguments.of(
                        "||,||,||,||,",
                        List.of("|", "|", "|", "|")
                ),
                Arguments.of(
                        "neet,code,love,you,",
                        List.of("neet", "code", "love", "you")
                ),
                Arguments.of(
                        "we,say,:,yes,",
                        List.of("we", "say", ":", "yes")
                )
        );
    }
}
