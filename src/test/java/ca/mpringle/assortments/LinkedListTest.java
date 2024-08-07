package ca.mpringle.assortments;

import ca.mpringle.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ca.mpringle.assortments.EqualsAdapter.typeAsEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class LinkedListTest {

    @Test
    void addAndRemoveShouldWork() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();

        assertNull(subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("a"));
        assertEquals(typeAsEquals("a"), subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("b"));
        assertEquals(typeAsEquals("b"), subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("c"));
        assertEquals(typeAsEquals("c"), subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("d"));
        assertEquals(typeAsEquals("d"), subjectUnderTest.get());

        subjectUnderTest.remove();
        assertEquals(typeAsEquals("c"), subjectUnderTest.get());
        subjectUnderTest.remove();
        assertEquals(typeAsEquals("b"), subjectUnderTest.get());
        subjectUnderTest.remove();
        assertEquals(typeAsEquals("a"), subjectUnderTest.get());
        subjectUnderTest.remove();
        assertNull(subjectUnderTest.get());
        subjectUnderTest.remove();
        assertNull(subjectUnderTest.get());

        new LinkedList<Pair<Integer, Integer>>().add(new Pair<>(1, 2));
    }

    @Test
    void addAllShouldAddListWithoutCopying() {

        final LinkedList<Equals<A>> subjectUnderTest = new LinkedList<>();
        final A one = new A();
        final A two = new A();
        final A three = new A();
        final A four = new A();
        subjectUnderTest.add(typeAsEquals(one));
        subjectUnderTest.add(typeAsEquals(two));

        final LinkedList<Equals<A>> toAppend = new LinkedList<>();
        toAppend.add(typeAsEquals(three));
        toAppend.add(typeAsEquals(four));

        subjectUnderTest.addAll(toAppend);

        final Iterator<Equals<A>> actual = subjectUnderTest.iterator();
        assertEquals(typeAsEquals(one), actual.next());
        assertEquals(typeAsEquals(two), actual.next());
        assertEquals(typeAsEquals(three), actual.next());
        assertEquals(typeAsEquals(four), actual.next());
        assertFalse(actual.hasNext());

        final Iterator<Equals<A>> iterator = toAppend.iterator();
        assertEquals(typeAsEquals(three), iterator.next());
        assertEquals(typeAsEquals(four), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorShouldWork() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();

        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final Iterator<Equals<String>> i = subjectUnderTest.iterator();

        assertTrue(i.hasNext());
        assertEquals(typeAsEquals("a"), i.next());
        assertTrue(i.hasNext());
        assertEquals(typeAsEquals("b"), i.next());
        assertTrue(i.hasNext());
        assertEquals(typeAsEquals("c"), i.next());
        assertTrue(i.hasNext());
        assertEquals(typeAsEquals("d"), i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void toCollectionTypeShouldWork() {

        final LinkedList<Equals<Integer>> subjectUnderTest = new LinkedList<>();

        assertEquals(0, subjectUnderTest.toArray(EqualsAdapter[]::new).length);

        for (int i = 0; i < 10; i++) {
            subjectUnderTest.add(EqualsAdapter.typeAsEquals(i));
        }

        final List<Equals<Integer>> actual = subjectUnderTest.toCollectionType(ArrayList::new);
        assertEquals(10, actual.size());
        actual.forEach(a -> assertTrue(subjectUnderTest.contains(a)));
    }

    @Test
    void toArrayShouldWork() {

        final LinkedList<Equals<Integer>> subjectUnderTest = new LinkedList<>();

        assertEquals(0, subjectUnderTest.toArray(EqualsAdapter[]::new).length);

        for (int i = 0; i < 10; i++) {
            subjectUnderTest.add(EqualsAdapter.typeAsEquals(i));
        }

        final Equals<Integer>[] actual = subjectUnderTest.toArray(Pair[]::new);
        assertEquals(10, actual.length);
        for (final Equals<Integer> equal : actual) {
            assertTrue(subjectUnderTest.contains(equal));
        }
    }

    @Test
    void equalsShouldWorkForNormalCase() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();
        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final LinkedList<Equals<String>> other = new LinkedList<>();
        other.add(typeAsEquals("a"));
        other.add(typeAsEquals("b"));
        other.add(typeAsEquals("c"));
        other.add(typeAsEquals("d"));

        assertEquals(other, subjectUnderTest);
    }

    @Test
    void equalsShouldReturnFalseWhenNull() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();

        assertNotEquals(subjectUnderTest, null);
    }

    @Test
    void toStringShouldHandleEmptyList() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();

        assertNotNull(subjectUnderTest.toString());
    }

    @Test
    void containsAnyShouldWork() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();
        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final LinkedList<Equals<String>> emptyList = new LinkedList<>();
        final LinkedList<Equals<String>> noMatch = new LinkedList<>();
        noMatch.add(typeAsEquals("e"));
        noMatch.add(typeAsEquals("f"));
        noMatch.add(typeAsEquals("g"));
        noMatch.add(typeAsEquals("h"));

        final LinkedList<Equals<String>> singleMatch = new LinkedList<>();
        singleMatch.add(typeAsEquals("e"));
        singleMatch.add(typeAsEquals("f"));
        singleMatch.add(typeAsEquals("g"));
        singleMatch.add(typeAsEquals("d"));

        final LinkedList<Equals<String>> superSet = new LinkedList<>();
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

    @Test
    void toStringShouldHaveCustomImplementation() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();
        subjectUnderTest.add(typeAsEquals("a"));
        final String unexpected = subjectUnderTest
                .getClass().getName() + "@" + Integer.toHexString(subjectUnderTest.hashCode());

        assertNotEquals(unexpected, subjectUnderTest.toString());
    }

    private static class A {
        // use this for instance equality testing
    }
}
