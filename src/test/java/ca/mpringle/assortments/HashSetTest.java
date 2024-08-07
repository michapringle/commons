package ca.mpringle.assortments;

import ca.mpringle.util.Pair;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static ca.mpringle.assortments.EqualsAdapter.typeAsEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
            subjectUnderTest.add(new BadHashEquals());
        }

        assertEquals(numberOfElements, subjectUnderTest.size());
    }

    @Test
    void addAllShouldWork() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();

        @SuppressWarnings("unchecked") final Equals<String>[] elements = new Equals[]{
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

        final Iterator<Equals<String>> iterator = subjectUnderTest.iterator();
        while (iterator.hasNext()) {
            final Equals<String> next = iterator.next();
            assertTrue(list.contains(next));
            list.remove(next);
        }

        assertTrue(list.isEmpty());
        assertThrows(
                NoSuchElementException.class,
                iterator::next
        );
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();
        subjectUnderTest.add(typeAsEquals("a"));
        final String unexpected = subjectUnderTest
                .getClass().getName() + "@" + Integer.toHexString(subjectUnderTest.hashCode());

        assertNotEquals(unexpected, subjectUnderTest.toString());
    }

    @Test
    void toCollectionTypeShouldWork() {

        final HashSet<Pair<Integer, Integer>> subjectUnderTest = new HashSet<>();

        assertEquals(0, subjectUnderTest.toArray(Pair[]::new).length);

        final int size = 14;
        for (int i = 0; i < size; i++) {
            subjectUnderTest.add(new Pair<>(i, i));
        }

        final List<Pair<Integer, Integer>> pairs = subjectUnderTest.toCollectionType(ArrayList::new);
        assertEquals(size, pairs.size());
        pairs.forEach(pair -> assertTrue(subjectUnderTest.contains(pair)));
    }

    @Test
    void toArrayShouldWork() {

        final HashSet<Pair<Integer, Integer>> subjectUnderTest = new HashSet<>();

        assertEquals(0, subjectUnderTest.toArray(Pair[]::new).length);

        final int size = 14;
        for (int i = 0; i < size; i++) {
            subjectUnderTest.add(new Pair<>(i, i));
        }

        final Pair<Integer, Integer>[] pairs = subjectUnderTest.toArray(Pair[]::new);
        assertEquals(size, pairs.length);
        for (final Pair<Integer, Integer> pair : pairs) {
            assertTrue(subjectUnderTest.contains(pair));
        }
    }

    @Test
    void equalsShouldWorkForNormalCase() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();
        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final HashSet<Equals<String>> other = new HashSet<>();
        other.add(typeAsEquals("a"));
        other.add(typeAsEquals("b"));
        other.add(typeAsEquals("c"));
        other.add(typeAsEquals("d"));

        assertEquals(other, subjectUnderTest);
    }

    @Test
    void equalsShouldReturnFalseWhenNull() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();

        assertNotEquals(subjectUnderTest, null);
    }

    @Test
    void containsAnyShouldWork() {

        final HashSet<Equals<String>> subjectUnderTest = new HashSet<>();
        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final HashSet<Equals<String>> emptyList = new HashSet<>();
        final HashSet<Equals<String>> noMatch = new HashSet<>();
        noMatch.add(typeAsEquals("e"));
        noMatch.add(typeAsEquals("f"));
        noMatch.add(typeAsEquals("g"));
        noMatch.add(typeAsEquals("h"));

        final HashSet<Equals<String>> singleMatch = new HashSet<>();
        singleMatch.add(typeAsEquals("e"));
        singleMatch.add(typeAsEquals("f"));
        singleMatch.add(typeAsEquals("g"));
        singleMatch.add(typeAsEquals("d"));

        final HashSet<Equals<String>> superSet = new HashSet<>();
        superSet.add(typeAsEquals("a"));
        superSet.add(typeAsEquals("b"));
        superSet.add(typeAsEquals("c"));
        superSet.add(typeAsEquals("d"));
        superSet.add(typeAsEquals("e"));

        assertFalse(subjectUnderTest.containsAny(emptyList));
        assertFalse(subjectUnderTest.containsAny(noMatch));
        assertTrue(subjectUnderTest.containsAny(singleMatch));
        assertTrue(subjectUnderTest.containsAny(superSet));
    }


    private static class BadHashEquals extends AbstractEquals<BadHashEquals> {

        public BadHashEquals() {
        }

        @Override
        public boolean isEqual(@Nullable final BadHashEquals instance) {

            return false;
        }

        @Override
        public int computeHash() {

            return 1;
        }
    }
}
