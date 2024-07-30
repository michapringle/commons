package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class Assortment<E extends Equals<?>> extends AbstractEquals<Assortment<E>> implements Iterable<E> {

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isEqual(@Nullable final Assortment<E> instance) {

        if (instance == null) {
            return false;
        }

        try {
            return this.size() == instance.size() && containsAll(instance);

        } catch (ClassCastException handled) {
            return false;
        }
    }

    @Override
    public int computeHash() {
        int hash = 0;
        for (final E element : this) {
            if (element != null)
                hash += element.hashCode();
        }
        return hash;
    }


    @NotNull
    @Override
    public abstract String toString();


//        Transform transform() {
//        return new Transform(this);
//    }

    public abstract boolean contains(@Nullable final E element);

    public boolean containsAll(@Nullable final Assortment<E> elements) {

        return elements
                .stream()
                .allMatch(this::contains);
    }

    public boolean containsAny(@Nullable final Assortment<E> elements) {

        return elements
                .stream()
                .anyMatch(this::contains);
    }

    @NotNull
    public Stream<E> stream() {

        return StreamSupport.stream(spliterator(), false);
    }

    @NotNull
    public Stream<E> parallelStream() {

        return StreamSupport.stream(spliterator(), true);
    }
}
