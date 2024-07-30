package study.neet.arraysandhashing;

import java.util.ArrayList;
import java.util.List;

final class EncodeDecode {

    private static final char WORD_SEPARATOR = ',';
    private static final char ESCAPE_CHARACTER = '|';

    // 1:40 -> 2:20
    public static String encode(final List<String> strings) {

        if (strings == null || strings.isEmpty()) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();

        for (final String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == WORD_SEPARATOR || string.charAt(i) == ESCAPE_CHARACTER) {
                    builder.append(ESCAPE_CHARACTER);
                }

                builder.append(string.charAt(i));
            }

            builder.append(WORD_SEPARATOR);
        }

        return builder.toString();
    }

    public static List<String> decode(final String string) {

        if (string == null) {
            return List.of();
        }

        StringBuilder builder = new StringBuilder();
        final List<String> strings = new ArrayList<>();

        for (int i = 0; i < string.length() - 1; i++) {

            if (string.charAt(i) == ESCAPE_CHARACTER && string.charAt(i + 1) == ESCAPE_CHARACTER) {
                builder.append(ESCAPE_CHARACTER);
                i++;

            } else if (string.charAt(i) == ESCAPE_CHARACTER && string.charAt(i + 1) == WORD_SEPARATOR) {
                builder.append(WORD_SEPARATOR);
                i++;
            } else if (string.charAt(i) == WORD_SEPARATOR) {
                strings.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(string.charAt(i));
            }
        }

        strings.add(builder.toString());
        return strings;
    }
}