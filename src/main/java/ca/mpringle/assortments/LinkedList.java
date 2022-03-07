package ca.mpringle.assortments;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Example linked list that uses Equals and supports the EqualsAdapter.
 * It is recommended not to write your own, but instead create a
 * decorator that follows the pattern here, for example a Set decorator.
 */
public final class LinkedList<E> {

    private Node<E> first;
    private Node<E> head;

    public LinkedList() {
        first = null;
        head = null;
    }

    public void add(final Equals<E> element) {

        if (head == null) {
            head = new Node<>(element, null, null);
            first = head;
        } else {
            final Node<E> n = new Node<>(element, null, head);
            head.next = n;
            head = n;
        }
    }

    public void append(final LinkedList<E> list) {

        head.next = list.first;
        head = list.head;
    }

    public void remove() {

        if (head != null) {
            head = head.previous;
        }
    }

    public Iterator<E> iterator() {

        return new Iterator<>() {

            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                final E element = node.element.get();
                node = node.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {

        final String string = head == null
                ? ""
                : toStream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + "->" + b)
                .substring(2);


        return String.format("[%s]", string);
    }

    public Stream<E> toStream() {

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED),
                false
        );
    }

    public E get() {

        return head == null ? null : head.element.get();
    }

    private static class Node<T> {

        private final Equals<T> element;
        private Node<T> next;
        private final Node<T> previous;

        public Node(Equals<T> element, Node<T> next, Node<T> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
}
