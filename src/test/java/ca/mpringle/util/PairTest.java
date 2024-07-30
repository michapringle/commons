package ca.mpringle.util;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class PairTest {

    @Test
    void constructorShouldWork() {

        assertDoesNotThrow(() -> new Pair<>(0, 0));
    }

    @Test
    void equalsAndHashcodeShouldObeyContract() {

        EqualsVerifier
                .forClass(Pair.class)
                .withRedefinedSuperclass()
                .verify();
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final Pair<Integer, Integer> sut = new Pair<>(1, 2);
        assertFalse(sut.toString().contains(Integer.toHexString(sut.hashCode())));
    }
}
