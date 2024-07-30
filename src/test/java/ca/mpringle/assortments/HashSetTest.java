package ca.mpringle.assortments;

import ca.mpringle.util.Pair;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ca.mpringle.assortments.EqualsAdapter.typeAsEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class HashSetTest {

    @Test
    void addShouldWorkForEqualsAdapter() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();

        for (int i = 1, c = 'a'; c <= 'z'; c++, i++) {

            // add twice, duplicates should be rejected
            final Equals<String> e = EqualsAdapter.typeAsEquals("" + (char) c);
            subjectUnderTest.add(e);
            subjectUnderTest.add(e);

            assertEquals(i, subjectUnderTest.size());
            assertTrue(subjectUnderTest.contains(e));
        }
    }

    @Test
    void addShouldWorkForEqualsExtension() {

        final HashSet<Pair<String, String>> subjectUnderTest = new HashSet<>();

        for (int i = 1, c = 'a'; c <= 'z'; c++, i++) {

            // add twice, duplicates should be rejected
            final Pair<String, String> e = new Pair<>("" + (char) c, "" + (char) c);
            subjectUnderTest.add(e);
            subjectUnderTest.add(e);

            assertEquals(i, subjectUnderTest.size());
            assertTrue(subjectUnderTest.contains(e));
        }
    }

    @Test
    void addShouldHandlePoorHashCode() {

        final HashSet<BadHashEquals> subjectUnderTest = new HashSet<>();

        final int numberOfElements = 10_000;
        for (int i = 0; i < numberOfElements; i++) {
            final BadHashEquals element = new BadHashEquals((char)i);
            subjectUnderTest.add(element);
        }

        assertEquals(numberOfElements, subjectUnderTest.size());
    }

    @Test
    void addAllShouldWork() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();

        final Equals<String>[] elements = new Equals[]{
                EqualsAdapter.typeAsEquals("a"),
                EqualsAdapter.typeAsEquals("b"),
                EqualsAdapter.typeAsEquals("c"),
                EqualsAdapter.typeAsEquals("d")
        };

        // add twice, duplicates should be rejected
        subjectUnderTest.addAll(elements);
        subjectUnderTest.addAll(elements);

        assertEquals(elements.length, subjectUnderTest.size());
        Arrays
                .stream(elements)
                .forEach(e -> assertTrue(subjectUnderTest.contains(e)));
    }

    @Test
    void iteratorShouldWork() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();

        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final List<Equals<String>> list = new ArrayList<>();
        list.add(typeAsEquals("a"));
        list.add(typeAsEquals("b"));
        list.add(typeAsEquals("c"));
        list.add(typeAsEquals("d"));

        for (final Equals<String> next : subjectUnderTest) {
            assertTrue(list.contains(next));
            list.remove(next);
        }
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();
        subjectUnderTest.add(typeAsEquals("a"));
        final String unexpected = subjectUnderTest
                .getClass().getName() + "@" + Integer.toHexString(subjectUnderTest.hashCode());

        assertNotEquals(unexpected, subjectUnderTest.toString());
    }

    private static class BadHashEquals extends AbstractEquals<String> {

        private final char c;

        public BadHashEquals(final char c) {
            this.c = c;
        }

        @Override
        public boolean isEqual(@Nullable final String instance) {

            return Objects.equals("" + c, instance);
        }

        @Override
        public int computeHash() {
            return 1;
        }
    }
}
