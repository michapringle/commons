package ca.mpringle.assortments;

import ca.mpringle.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
    void appendShouldAddListWithoutCopying() {

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

        subjectUnderTest.append(toAppend);

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
    void toStringShouldHandleEmptyList() {

        final LinkedList<Equals<String>> subjectUnderTest = new LinkedList<>();

        assertNotNull(subjectUnderTest.toString());
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
