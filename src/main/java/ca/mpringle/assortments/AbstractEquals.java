package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

/**
 * An abstract implementation of (@link Equals<T>} to avoid the nuisance of
 * having to implement 4 methods when only 2 should be required. Prefer using
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
     * no implementation required
     */
    @Override
    public int hashCode() {
        return computeHash();
    }

    /**
     * no implementation required
     */
    @Override
    public boolean equals(@Nullable final Object instance) {

        try {
            return isEqual((T) instance);
        } catch (ClassCastException handled) {
            return false;
        }
    }
}
