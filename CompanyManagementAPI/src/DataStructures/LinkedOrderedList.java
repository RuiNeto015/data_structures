package DataStructures;

import DataStructuresInterfaces.OrderedListADT;

/**
 *
 * @author Simão
 * @param <T>
 */
public class LinkedOrderedList<T> extends LinkedList<T> implements OrderedListADT<T> {

    /**
     * Adds the specified element to this list at the proper location
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(T element) {
        if (!(element instanceof Comparable)) {
            return;
        }
        Node<T> newNode = new Node<>(element);
        Comparable tmp = (Comparable) element;

        if (super.isEmpty()) {
            super.head = newNode;
            super.tail = newNode;
        } else if (tmp.compareTo(super.head.getData()) < 0) {
            Node<T> oldHead = super.head;
            super.head = newNode;
            super.head.setNext(oldHead);

        } else if (tmp.compareTo(super.tail.getData()) > 0) {
            super.tail.setNext(newNode);
            super.tail = super.tail.getNext();
        } else {
            Node<T> n = super.head;
            while (n.getNext() != null && tmp.compareTo(n.getData()) >= 0) {
                n = n.getNext();
            }
            Node<T> next = n.getNext();
            n.setNext(newNode);
            newNode.setNext(next);
        }
        super.modCount++;
        super.counter++;
    }
}
