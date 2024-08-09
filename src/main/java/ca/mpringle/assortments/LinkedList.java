package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Example linked list that uses Equals and supports the EqualsAdapter.
 * It is recommended not to write your own, but instead create a
 * decorator that follows the pattern here, for example a Set decorator.
 */
public final class LinkedList<E extends Equals<?>> extends AbstractAssortment<E> {

    @Nullable
    private Node<E> first;
    @Nullable
    private Node<E> head;
    private int size;

    public LinkedList() {
        first = null;
        head = null;
        size = 0;
    }

    public void add(@Nullable final E element) {

        if (head == null) {
            head = new Node<>(element, null);
            first = head;
        } else {
            final Node<E> n = new Node<>(element, head);
            head.next = n;
            // make head the latest node
            head = n;
        }
        size++;
    }

    public void addAll(@NotNull final LinkedList<E> list) {

        head.next = list.first;
        head = list.head;
        size += list.size();
    }

    public void remove() {

        if (head != null) {
            head = head.previous;
        }
        if (head != null) {
            head.next = null;
        }
        size--;
    }

    @Override
    @NotNull
    public Iterator<E> iterator() {

        return new Iterator<>() {

            @NotNull
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            @Nullable
            public E next() {

                if (node == null) {
                    throw new NoSuchElementException("The iterator is exhausted, no more elements.");
                }

                final E element = node.element;
                node = node.next;
                return element;
            }
        };
    }

    @Override
    @NotNull
    public String toString() {

        final String string = head == null
                ? ""
                : stream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + "->" + b)
                .substring(2);


        return String.format("[%s]", string);
    }


    @Nullable
    public E get() {

        return head == null ? null : head.element;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E2 extends Equals<?>> {

        @Nullable
        private final E2 element;
        @Nullable
        private Node<E2> next;
        @Nullable
        private final Node<E2> previous;

        public Node(@Nullable final E2 element, @Nullable final Node<E2> previous) {
            this.element = element;
            this.next = null;
            this.previous = previous;
        }
    }
}
