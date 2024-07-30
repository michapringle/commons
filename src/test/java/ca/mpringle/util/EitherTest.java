package ca.mpringle.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class EitherTest {

    @Test
    void newLeftShouldEnforceClassInvariants() {

        assertDoesNotThrow(() -> Either.newLeft(null));
        assertDoesNotThrow(() -> Either.newLeft("a"));
    }

    @Test
    void newRightShouldEnforceClassInvariants() {

        assertDoesNotThrow(() -> Either.newRight(null));
        assertDoesNotThrow(() -> Either.newRight("a"));
    }

    @Test
    void isLeftShouldWork() {

        assertTrue(Either.newLeft(null).isLeft());
        assertFalse(Either.newRight(null).isLeft());
        assertEquals("a", Either.newLeft("a").getLeft());
        assertNull(Either.newLeft("a").getRight());
    }

    @Test
    void isRightShouldWork() {

        assertFalse(Either.newLeft(null).isRight());
        assertTrue(Either.newRight(null).isRight());
        assertNull(Either.newRight("a").getLeft());
        assertEquals("a", Either.newRight("a").getRight());
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final Either<Integer, Integer> sut = Either.newLeft(1);
        assertFalse(sut.toString().contains(Integer.toHexString(sut.hashCode())));
    }
}
