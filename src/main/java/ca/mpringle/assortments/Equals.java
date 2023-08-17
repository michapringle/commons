package ca.mpringle.assortments;

/**
 * An interface for custom {@link Equals<T>#equals(Object)} and {@link Equals<T>#hashcode}
 * implementations. In order to integrate with the existing {@link Object} class, the class
 * requires the implementor to override the {@link Equals<T>#equals(Object)} and
 * {@link Equals<T>#hashcode} methods, instead of a type safe equals method.
 * <p>
 * A user of this class can use to the new type safe equals method {@link Equals<T>#verifyEquality(T)}
 * The verbose name is to ensure there is no confusion between the old and new methods.
 *
 * @param <T> Your class that you want compile-time equals checking on.
 */
public interface Equals<T> {

    /**
     * No custom implementation needed, this method delegates to the old equals method
     */
    default boolean verifyEquality(final T instance) {

        return equals(instance);
    }

    /**
     * The {@link EqualsAdapter<T>} used for backward compatibility with the original way of handing
     * equals and hashcode requires this class.
     * <p>
     * The implementation will generally look like
     * <pre>
     *
     *     &#64;Override
     *     public T get() {
     *         return this;
     *    }
     * </pre>
     */
    T get();

    @Override
    boolean equals(final Object instance);

    @Override
    int hashCode();
}
