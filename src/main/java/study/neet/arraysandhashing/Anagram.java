package study.neet.arraysandhashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

final class Anagram {

    private Anagram() {
    }

    // 2:32 -> 3:10 - anagram part was written already
    static List<List<String>> groupAnagrams(final String[] strings) {

        final Map<String, List<String>> mappings = new HashMap<>();

        Arrays
                .stream(strings)
                .forEach(s -> {
                    final String homogeneous = convertToHomogeneousForm(s);
                    if (mappings.containsKey(homogeneous)) {
                        mappings.get(homogeneous).add(s);
                    } else {
                        final List<String> group = new ArrayList<>();
                        group.add(s);
                        mappings.put(homogeneous, group);
                    }
                });

        return new ArrayList<>(mappings.values());
    }

    static boolean isAnagram(final String string1, final String string2) {

        final String s1 = convertToHomogeneousForm(string1);
        final String s2 = convertToHomogeneousForm(string2);
        return Objects.equals(s1, s2);
    }

    private static String convertToHomogeneousForm(final String source) {

        final char[] chars = source.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}


