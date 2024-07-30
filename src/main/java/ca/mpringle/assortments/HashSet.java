package ca.mpringle.assortments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashSet<E extends Equals<?>> extends Assortment<E> {

    // this value must be > 0
    private static final int INITIAL_CAPACITY = 3;
    private List<List<E>> list;
    private int size;

    public HashSet() {
        this(INITIAL_CAPACITY);
    }

    public HashSet(final int initialCapacity) {
        list = createEmptyListOfSize(initialCapacity);
        size = 0;
    }

    public void add(final E element) {

        if (contains(element)) {
            return;
        }

        final int index = element.hashCode() % list.size();
        list.get(index).add(element);
        size++;
        resizeIfRequired();
    }

    public void add(final HashSet<E> hashSet) {

        for (final List<E> sublist : hashSet.list) {
            for (final E element : sublist) {
                add(element);
            }
        }
    }

    @Override
    public boolean contains(final E element) {

        final int index = element.hashCode() % list.size();
        // could be a problem if the Equals<T> interface is implemented and
        // the developer does not write a custom equals or hashcode
        return list.get(index).stream().anyMatch(e -> e.equals(element));
    }

    public void addAll(final E... elements) {

        for (final E element : elements) {
            add(element);
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
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

                return null;
            }
        };
    }

    @Override
    public String toString() {

        final StringBuilder indexes = new StringBuilder();
        final StringBuilder arrows = new StringBuilder();
        final StringBuilder elements = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            indexes.append(" ").append(i);
            arrows.append(" ↓");
            elements.append("(");
            int indexLengthSoFar = indexes.length();
            int arrowsLengthSoFar = arrows.length();
            for (final E element : list.get(i)) {
                elements.append(element == null ? " " : element.toString()).append(",");
            }
            if (list.get(i).isEmpty()) {
                elements.append(" ,");
            }
            elements.deleteCharAt(elements.length() - 1);
            elements.append(") ");
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

        // a somewhat lax function to restrict the size of the hashset.
        if (size > list.size() * 3) {
            final HashSet<E> hashSet = new HashSet<>(list.size() * 2);
            hashSet.add(this);
            list = hashSet.list;
        }
    }

    private List<List<E>> createEmptyListOfSize(final int size) {

        final List<List<E>> newList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            newList.add(new ArrayList<>());
        }

        return newList;
    }
}