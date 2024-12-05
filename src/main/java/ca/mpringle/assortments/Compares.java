package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;

/**
 * {@link Comparable}
 */
public interface Compares<T> extends Equals<T> {

    /**
     * {@link Comparable#compareTo(Object)}
     */
    int compareTo(@NotNull final T instance);

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isEqual(@Nullable final T instance) {

        return compareTo(instance) == 0;
    }
}
