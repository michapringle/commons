package ca.mpringle.assortments;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

final class EqualsAdapterTest {

    @Test
    void equalsAndHashCodeShouldWork() {

        EqualsVerifier
                .forClass(EqualsAdapter.class)
                .withRedefinedSuperclass()
                .verify();
    }

    @Test
    void getValueShouldBeAccessibleWithoutCast() {

        final EqualsAdapter<String> subjectUnderTest = EqualsAdapter.typeAsEquals("a");

        assertEquals("a", subjectUnderTest.getValue());
    }

    @Test
    void toStringShouldHaveCustomImplementation() {

        final Equals<String> subjectUnderTest = EqualsAdapter.typeAsEquals("a");
        final String unexpected = subjectUnderTest
                .getClass().getName() + "@" + Integer.toHexString(subjectUnderTest.hashCode());

        assertNotEquals(unexpected, subjectUnderTest.toString());
    }
}
