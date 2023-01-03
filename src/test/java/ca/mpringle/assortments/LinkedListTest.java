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

        final LinkedList<String> subjectUnderTest = new LinkedList<>();

        assertNull(subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("a"));
        assertEquals("a", subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("b"));
        assertEquals("b", subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("c"));
        assertEquals("c", subjectUnderTest.get());
        subjectUnderTest.add(typeAsEquals("d"));
        assertEquals("d", subjectUnderTest.get());

        subjectUnderTest.remove();
        assertEquals("c", subjectUnderTest.get());
        subjectUnderTest.remove();
        assertEquals("b", subjectUnderTest.get());
        subjectUnderTest.remove();
        assertEquals("a", subjectUnderTest.get());
        subjectUnderTest.remove();
        assertNull(subjectUnderTest.get());
        subjectUnderTest.remove();
        assertNull(subjectUnderTest.get());

        new LinkedList<Pair<Integer, Integer>>().add(new Pair<>(1, 2));
    }

    @Test
    void appendShouldAddListWithoutCopying() {

        final LinkedList<A> subjectUnderTest = new LinkedList<>();
        final A one = new A();
        final A two = new A();
        final A three = new A();
        final A four = new A();
        subjectUnderTest.add(typeAsEquals(one));
        subjectUnderTest.add(typeAsEquals(two));

        final LinkedList<A> toAppend = new LinkedList<>();
        toAppend.add(typeAsEquals(three));
        toAppend.add(typeAsEquals(four));

        subjectUnderTest.append(toAppend);

        final Iterator<A> actual = subjectUnderTest.iterator();
        assertEquals(one, actual.next());
        assertEquals(two, actual.next());
        assertEquals(three, actual.next());
        assertEquals(four, actual.next());
        assertFalse(actual.hasNext());

        final Iterator<A> iterator = toAppend.iterator();
        assertEquals(three, iterator.next());
        assertEquals(four, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorShouldWork() {

        final LinkedList<String> subjectUnderTest = new LinkedList<>();

        subjectUnderTest.add(typeAsEquals("a"));
        subjectUnderTest.add(typeAsEquals("b"));
        subjectUnderTest.add(typeAsEquals("c"));
        subjectUnderTest.add(typeAsEquals("d"));

        final Iterator<String> i = subjectUnderTest.iterator();

        assertTrue(i.hasNext());
        assertEquals("a", i.next());
        assertTrue(i.hasNext());
        assertEquals("b", i.next());
        assertTrue(i.hasNext());
        assertEquals("c", i.next());
        assertTrue(i.hasNext());
        assertEquals("d", i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void toStringShouldHandleEmptyList() {

        final LinkedList<String> subjectUnderTest = new LinkedList<>();

        assertNotNull(subjectUnderTest.toString());
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final LinkedList<String> subjectUnderTest = new LinkedList<>();
        subjectUnderTest.add(typeAsEquals("a"));
        final String unexpected = subjectUnderTest
                .getClass().getName() + "@" + Integer.toHexString(subjectUnderTest.hashCode());

        assertNotEquals(unexpected, subjectUnderTest.toString());
    }

    private static class A {
        // use this for instance equality testing
    }
}
