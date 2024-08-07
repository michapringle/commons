package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class HashSet<E extends Equals<?>> extends AbstractAssortment<E> {

    // this value must be > 0
    private static final int INITIAL_CAPACITY = 3;

    @NotNull
    private List<List<E>> list;
    private int size;

    public HashSet() {
        this(INITIAL_CAPACITY);
    }

    public HashSet(final int initialCapacity) {
        list = createEmptyListOfSize(initialCapacity);
        size = 0;
    }

    public void add(@Nullable final E element) {

        if (contains(element)) {
            return;
        }

        final int index = element.hashCode() % list.size();
        list.get(index).add(element);
        size++;
        resizeIfRequired();
    }

    public void addAll(@NotNull final HashSet<E> hashSet) {

        for (final List<E> sublist : hashSet.list) {
            for (final E element : sublist) {
                add(element);
            }
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean contains(@Nullable final E element) {

        if (element == null) {
            return false;
        }

        final int index = element.hashCode() % list.size();
        return list.get(index).stream().anyMatch(e -> ((Equals) e).isEqual(element));
    }

    @SafeVarargs
    public final void addAll(@NotNull final E... elements) {

        for (final E element : elements) {
            add(element);
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    @NotNull
    public Iterator<E> iterator() {

        return new Iterator<>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            @Nullable
            public E next() {
                int sum = 0;
                for (final List<E> sublist : list) {
                    sum += sublist.size();
                    if (index < sum) {
                        final E element = sublist.get(sublist.size() - sum + index);
                        index++;
                        return element;
                    }
                }

                throw new NoSuchElementException("The iterator is exhausted, no more elements.");
            }
        };
    }

    @Override
    @NotNull
    public String toString() {

        final StringBuilder indexes = new StringBuilder();
        final StringBuilder arrows = new StringBuilder();
        final StringBuilder elements = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            indexes.append(i).append(" ");
            arrows.append("↓ ");
            elements.append("{");
            int indexLengthSoFar = indexes.length();
            int arrowsLengthSoFar = arrows.length();
            for (final E element : list.get(i)) {
                elements.append(element == null ? " " : element.toString()).append(",");
            }
            if (!list.get(i).isEmpty()) {
                elements.deleteCharAt(elements.length() - 1);
            }
            elements.append("} ");
            indexes.append(" ".repeat(Math.max(0, elements.length() - indexLengthSoFar)));
            arrows.append(" ".repeat(Math.max(0, elements.length() - arrowsLengthSoFar)));
        }

        indexes.append(System.lineSeparator());
        arrows.append(System.lineSeparator());

        return indexes.toString() + arrows + elements;
    }

    private void resizeIfRequired() {

        final long emptyBuckets = list
                .stream()
                .filter(List::isEmpty)
                .count();

        // handle sparse results from crappy hashes by not resizing
        final double percentageOfEmptyBuckets = emptyBuckets * 1.0D / list.size();
        if (percentageOfEmptyBuckets > 0.83) {
            return;
        }

        // allow easy substitution of sizing algorithms
        final Supplier<Boolean> sizingAlgorithm = () -> size > list.size() * 3;

        if (sizingAlgorithm.get()) {
            final HashSet<E> hashSet = new HashSet<>(list.size() * 2);
            hashSet.addAll(this);
            list = hashSet.list;
        }
    }

    @NotNull
    private List<List<E>> createEmptyListOfSize(final int size) {

        final List<List<E>> newList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            newList.add(new ArrayList<>());
        }

        return newList;
    }
}