package ca.mpringle.util;

import ca.mpringle.assortments.AbstractEquals;
import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class Pair<A, B> extends AbstractEquals<Pair<A, B>> {

    @Nullable
    private final A a;
    @Nullable
    private final B b;

    public Pair(@Nullable final A a,
                @Nullable final B b) {

        this.a = a;
        this.b = b;
    }

    @Nullable
    public A getFirst() {
        return a;
    }

    @Nullable
    public B getSecond() {
        return b;
    }

    @Override
    public boolean isEqual(@Nullable final Pair<A, B> instance) {

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
