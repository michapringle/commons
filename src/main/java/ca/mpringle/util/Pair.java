package ca.mpringle.util;

import ca.mpringle.assortments.AbstractEquals;
import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class Pair<E1, E2> extends AbstractEquals<Pair<E1, E2>> {

    @Nullable
    private final E1 a;
    @Nullable
    private final E2 b;

    public Pair(@Nullable final E1 a,
                @Nullable final E2 b) {

        this.a = a;
        this.b = b;
    }

    @Nullable
    public E1 getFirst() {
        return a;
    }

    @Nullable
    public E2 getSecond() {
        return b;
    }

    @Override
    public boolean isEqual(@Nullable final Pair<E1, E2> instance) {

        if (instance == null) {
            return false;
        }

        return Objects.equals(this.a, instance.a) && Objects.equals(this.b, instance.b);
    }

    @Override
    public int computeHash() {

        return Objects.hash(a, b);
    }

    @Override
    @NotNull
    public String toString() {

        return String.format("(%s, %s)", a, b);
    }
}
