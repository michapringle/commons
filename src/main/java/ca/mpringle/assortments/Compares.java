package ca.mpringle.assortments;

import javax.validation.constraints.NotNull;

/**
 * {@link Comparable}
 */
public interface Compares<T> extends Equals<T> {

    /**
     * {@link Comparable#compareTo(Object)}
     */
    int compareTo(@NotNull final T instance);
}
