package ca.mpringle.util;

import ca.mpringle.assortments.Equals;

import java.util.Objects;

public final class Pair<E1, E2> implements Equals<Pair<E1, E2>> {

    public final E1 a;
    public final E2 b;

    public Pair(final E1 a,
                final E2 b) {

        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(final Object o) {

        if (o instanceof Pair) {

            final Pair<?, ?> that = (Pair<?, ?>) o;
            return Objects.equals(this.a, that.a) && Objects.equals(this.b, that.b);
        }
        return false;
    }

    @Override
    public Pair<E1, E2> get() {
        return this;
    }

    @Override
    public int hashCode() {

        return Objects.hash(a, b);
    }

    @Override
    public String toString() {

        return String.format("(%s, %s)", a, b);
    }
}
