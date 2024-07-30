package ca.mpringle.assortments;

import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Example linked list that uses Equals and supports the EqualsAdapter.
 * It is recommended not to write your own, but instead create a
 * decorator that follows the pattern here, for example a Set decorator.
 */
public final class LinkedList<E extends Equals<?>> extends Assortment<E> {

    private Node<E> first;
    private Node<E> head;
    private int size;

    public LinkedList() {
        first = null;
        head = null;
        size = 0;
    }

    public void add(final E element) {

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

    public void append(@NotNull final LinkedList<E> list) {

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
    public Iterator<E> iterator() {

        return new Iterator<>() {

            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                final E element = node.element;
                node = node.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {

        final String string = head == null
                ? ""
                : stream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + "->" + b)
                .substring(2);


        return String.format("[%s]", string);
    }


    public E get() {

        return head == null ? null : head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(@Nullable final E element) {

        // could be a problem if the Equals interface is implemented and
        // the developer does not write a custom equals or hashcode
        return stream().anyMatch(e -> e.equals(element));
    }

    private static class Node<E2 extends Equals<?>> {

        private final E2 element;
        private Node<E2> next;
        private final Node<E2> previous;

        public Node(final E2 element, final Node<E2> previous) {
            this.element = element;
            this.next = null;
            this.previous = previous;
        }
    }
}
