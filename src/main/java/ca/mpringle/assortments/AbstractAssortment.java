package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractAssortment<E extends Equals<?>> extends AbstractEquals<AbstractAssortment<E>> implements Iterable<E> {

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isEqual(@Nullable final AbstractAssortment<E> instance) {

        if (instance == null) {
            return false;
        }

        return this.size() == instance.size() && containsAll(instance);
    }

    @Override
    public int computeHash() {

        return stream()
                .filter(Objects::nonNull)
                .map(e -> e.computeHash())
                .reduce(0, Integer::sum);
    }

    @NotNull
    @Override
    public abstract String toString();

    @NotNull
    public <C extends Collection<E>> C toCollectionType(@NotNull final Supplier<C> collectionFactory) {

        return stream().collect(Collectors.toCollection(collectionFactory));
    }

    /**
     * Example usage: instance.toArray(String[]::new);
     */
    @NotNull
    public E[] toArray(@NotNull final IntFunction<E[]> generator) {

        if (isEmpty()) {
            final Class<?> componentType = generator.apply(0).getClass().getComponentType();
            @SuppressWarnings("unchecked") final E[] array = (E[]) Array.newInstance(componentType, 0);
            return array;
        }

        final Iterator<E> iterator = iterator();
        final E element = iterator.next();
        @SuppressWarnings("unchecked") final E[] array = (E[]) Array.newInstance(element.getClass(), size());
        array[0] = element;

        int index = 1;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }

        return array;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean contains(@Nullable final E element) {

        return stream().anyMatch(e -> ((Equals) e).isEqual(element));
    }

    public boolean containsAll(@Nullable final AbstractAssortment<E> elements) {

        return elements
                .stream()
                .allMatch(this::contains);
    }

    public boolean containsAny(@Nullable final AbstractAssortment<E> elements) {

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
