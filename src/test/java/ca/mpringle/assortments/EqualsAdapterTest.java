package ca.mpringle.assortments;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

final class EqualsAdapterTest {

    @Test
    void equalsAndHashCodeShouldWork() {

        EqualsVerifier
                .forClass(EqualsAdapter.class)
                .withRedefinedSuperclass()
                .verify();
    }
}
