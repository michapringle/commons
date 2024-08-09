package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

/**
 * An abstract implementation of (@link Equals<T>} to avoid the nuisance of
 * having to implement 4 methods when only 2 should be required. Prefer
 * this class to the interface where possible.
 */
public abstract class AbstractEquals<T> implements Equals<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isEqual(@Nullable final T instance);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract int computeHash();

    /**
     * Delegates to {@link AbstractEquals#computeHash()}. Custom implementations
     * are not allowed.
     */
    @Override
    public final int hashCode() {
        return computeHash();
    }

    /**
     * Delegates to {@link AbstractEquals#isEqual(T)}. Custom implementations
     * are not allowed.
     */
    @Override
    public final boolean equals(@Nullable final Object instance) {

        try {
            @SuppressWarnings("unchecked") final boolean result = isEqual((T) instance);
            return result;
        } catch (ClassCastException handled) {
            return false;
        }
    }
}
