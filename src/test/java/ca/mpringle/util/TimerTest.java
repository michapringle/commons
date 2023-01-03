package ca.mpringle.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class TimerTest {

    @Test
    void splitShouldWorkWhenCalled() {

        final Timer subjectUnderTest = Timer.createStarted();
        subjectUnderTest.split();
        subjectUnderTest.split();

        assertEquals(2, subjectUnderTest.getElapsedTimesInMillis().size());
    }

    @Test
    void getSplitShouldHaveMonotonicNonDecreasingSplitsWhenCalled() {

        final Timer subjectUnderTest = Timer.createStarted();
        subjectUnderTest.split();
        subjectUnderTest.split();
        subjectUnderTest.split();

        assertTrue(subjectUnderTest.getSplit(1) >= subjectUnderTest.getSplit(0));
        assertTrue(subjectUnderTest.getSplit(2) >= subjectUnderTest.getSplit(1));
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final Timer subjectUnderTest = Timer.createStarted();
        assertFalse(subjectUnderTest.toString().contains(Integer.toHexString(subjectUnderTest.hashCode())));

        subjectUnderTest.split();
        assertFalse(subjectUnderTest.toString().contains(Integer.toHexString(subjectUnderTest.hashCode())));
    }
}
