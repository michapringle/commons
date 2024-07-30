package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

/**
 * An interface for custom equals and hashcode implementations. By extracting equals
 * and hashcode to an interface, classes can make an explicit requirement for custom
 * implementations of those methods instead of the defaults provided by {@link Object}.
 * This provides compile time type checking, which is better than discovering an error
 * at runtime, especially in a production environment.
 * <p>
 * Due to language limitations, implementations of this interface require implementing
 * {@link Object#equals(Object)} and {@link Object#hashCode} to support
 * the methods from the {@link Object} class, as well as this interfaces analogous
 * methods {@link Equals<T>#isEqual(T)} and {@link Equals<T>#computeHash()}. These new,
 * separate methods have been added to force the developer to provide the required custom
 * implementation.
 * <p>
 * If your implementation is not already extending another class, it is recommended to use
 * {@link AbstractEquals} instead of this interface. This will require you to implement
 * {@link AbstractEquals<T>#isEqual(T)} and {@link AbstractEquals<T>#computeHash()} only.
 * <p>
 *
 * @param <T> Your class that you want compile-time equals checking on.
 */
public interface Equals<T> {

    /**
     * {@link Object#equals(Object)}
     */
    boolean isEqual(@Nullable final T instance);

    /**
     * {@link Object#hashCode()}
     */
    int computeHash();

    /**
     * If possible, prefer to use the abstract implementation of this interface
     * {@link AbstractEquals}. If this is not possible, your implementation should be
     * <pre>
     *    &#64;Override
     *    public boolean equals(@Nullable final Object instance) {
     *
     *       try {
     *          return isEqual((T) instance);
     *       } catch (ClassCastException handled) {
     *          return false;
     *       }
     *    }
     * </pre>
     */
    @Override
    boolean equals(@Nullable final Object instance);

    /**
     * If possible, prefer to use the abstract implementation of this interface
     * {@link AbstractEquals}. If this is not possible, your implementation should be
     * <pre>
     *   &#64;Override
     *   public int hashCode() {
     *      return computeHash();
     *   }
     * </pre>
     */
    @Override
    int hashCode();
}
