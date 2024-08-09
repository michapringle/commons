package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * This idea is found in Google Guava library, but was developed independently here.
 * <p>
 * Provides a way to adapt an existing Java class that must define a custom equals and hashcode into a
 * type that guarantees a custom implementation. This is desirable because the classes that require
 * custom equals and hashCode methods can enforce at compile time that the user of the class provides
 * a custom implementation for equals and hashCode.
 * <p>
 * Ultimately, the difference between using this class and the default methods from Object is one of
 * communication. For example, assortments classes will stop you at compile time from adding an element
 * that is not of type Equals, but there is nothing stopping you from using this class to adapt an
 * existing class that has no custom equals or hashcode implementation into an Equals that supposedly
 * does. See the examples below.
 * <pre>
 *
 *  // Your new class without a custom equals or hashcode implementation
 *  // Do not use the EqualsAdapter
 *  public final MyContrivedExample {
 *      final Something something;
 *
 *      public MyContrivedExample(final Something something) {
 *          this.something = something;
 *      }
 *  }
 *
 *  // Your new class with a custom equals and hashcode implementation
 *  // Do not use the EqualsAdapter, implement Equals
 *  public final MyClass implements Equals&#60;MyClass&#62; {
 *       ...
 *  }
 *
 *  // An existing java class with a custom equals and hashcode implementation
 *  // Use the EqualsAdapter as necessary
 *  public final class String
 *     implements java.io.Serializable, Comparable&#60;String&#62;, CharSequence {
 *      ...
 *  }
 *
 *  // An existing java class without a custom equals or hashcode implementation
 *  // Do not use the EqualsAdapter
 *  public class LockSupport {
 *       ...
 *  }
 * </pre>
 */
public final class EqualsAdapter<T> extends AbstractEquals<EqualsAdapter<T>> {

    @Nullable
    private final T instance;

    private EqualsAdapter(@Nullable final T instance) {

        this.instance = instance;
    }

    public static <S extends Equals<?>, E> S typeAsEquals(@Nullable final E instance) {

        @SuppressWarnings("unchecked") final S s = (S) new EqualsAdapter<>(instance);
        return s;
    }

    @Nullable
    T getValue() {
        return instance;
    }

    @Override
    public boolean isEqual(@Nullable final EqualsAdapter<T> otherInstance) {

        if (otherInstance == null) {
            return false;
        }

        return Objects.equals(this.instance, otherInstance.instance);
    }

    @Override
    public int computeHash() {
        return Objects.hash(instance);
    }

    @Nullable
    @Override
    public String toString() {
        return instance == null ? null : instance.toString();
    }
}
