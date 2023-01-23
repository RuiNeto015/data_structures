package DataStructures;

import DataStructuresInterfaces.UnorderedListADT;

/**
 *
 * @author Simão
 * @param <T>
 */
public class LinkedUnorderedList<T> extends LinkedList<T>
        implements UnorderedListADT<T> {

    /**
     * Adds an element to the front of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToFront(T element) {
        Node<T> node = new Node<>(element);

        if (super.isEmpty()) {
            super.head = node;
            super.tail = node;
        } else {
            Node oldhead = this.head;
            super.head = node;
            super.head.setNext(oldhead);
        }
        super.counter++;
        super.modCount++;
    }

    /**
     * Adds an element to the rear of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToRear(T element) {
        Node<T> node = new Node<>(element);

        if (super.isEmpty()) {
            super.head = node;
            super.tail = node;
        } else {
            super.tail.setNext(node);
            super.tail = this.tail.getNext();
        }
        super.counter++;
        super.modCount++;
    }

    /**
     * Adds an element after an element of the list.
     *
     * @param element the element to be added
     * @param previous the previous element of the new element.
     */
    @Override
    public void addAfter(T element, T previous) {
        if (!super.contains(previous)) {
            return;
        }
        Node<T> node = new Node<>(element);
        Node<T> n = super.head;
        while (n != null && n.getData() != previous) {
            n = n.getNext();
        }

        Node<T> next = n.getNext();
        n.setNext(node);
        if (next != null) {
            node.setNext(next);
        } else {
            this.tail = node;
        }
        super.counter++;
        super.modCount++;
    }

}
