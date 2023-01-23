package DataStructures;

import DataStructuresInterfaces.ListADT;
import java.util.Iterator;

/**
 *
 * @author Simão
 * @param <T>
 */
public class LinkedList<T> implements ListADT<T> {

    /**
     * Points to the LinkedList head.
     */
    protected Node<T> head;

    /**
     * Points to the LinkedList tail.
     */
    protected Node<T> tail;

    /**
     * Counts the number of nodes.
     */
    protected int counter;

    /**
     * Counts the number of operations.
     */
    protected int modCount;

    /**
     * Constructor for the LinkedList class.
     */
    public LinkedList() {
        this.counter = 0;
        this.modCount = 0;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T removed = this.head.getData();

        if (this.counter == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.getNext();
        }
        this.counter--;
        this.modCount++;
        return removed;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T removed = this.tail.getData();

        if (this.counter == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node n = this.head;
            while (n.getNext() != this.tail) {
                n = n.getNext();
            }
            this.tail = n;
            n.setNext(null);
        }
        this.counter--;
        this.modCount++;
        return removed;
    }

    /**
     * Removes and returns the specified element from this list.
     *
     * @param element the element to be removed from the list
     * @return the element that was removed
     */
    @Override
    public T remove(T element) {
        if ((!this.contains(element)) || this.isEmpty()) {
            return null;
        }

        if (this.head.getData().equals(element)) {
            return this.removeFirst();
        }

        if (this.tail.getData().equals(element)) {
            return this.removeLast();
        }

        Node<T> n = this.head;
        while (n != null && n.getNext().getData() != element) {
            n = n.getNext();
        }
        n.setNext(n.getNext().getNext());
        this.counter--;
        this.modCount++;
        return element;
    }

    /**
     * Returns a reference to the first element in this list.
     *
     * @return a reference to the first element in this list
     */
    @Override
    public T first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.head.getData();
    }

    /**
     * Returns a reference to the last element in this list.
     *
     * @return a reference to the last element in this list
     */
    @Override
    public T last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.tail.getData();
    }

    /**
     * Returns true if this list contains the specified target element.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    @Override
    public boolean contains(T target) {
        if (this.isEmpty()) {
            return false;
        }
        Node<T> n = this.head;
        while (n != null) {
            if (target.equals(n.getData())) {
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.counter == 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    @Override
    public int size() {
        return this.counter;
    }

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this.modCount);
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return null;
        }
        
        String str = "";
        Node n = this.head;

        while (n.getNext() != null) {
            str += n.getData() + "; ";
            n = n.getNext();
        }
        str += n.getData();
        return str;
    }

    private class LinkedListIterator<E> implements Iterator<E> {

        private int expectedModcount;
        private Node<E> current;
        private boolean okToRemove;

        private LinkedListIterator(int modCount) {
            this.expectedModcount = modCount;
            this.current = new Node<>(null);
            this.current.setNext((Node<E>) LinkedList.this.head);
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.current.getNext() != null;
        }

        @Override
        public E next() {
            if (this.expectedModcount != LinkedList.this.modCount) {
                throw new IllegalStateException();
            }

            if (!this.hasNext()) {
                return null;
            }
            this.okToRemove = true;
            this.current = this.current.getNext();
            return this.current.getData();
        }

        @Override
        public void remove() {
            if (!this.okToRemove) {
                return;
            }
            LinkedList.this.remove((T) this.current.getData());
            this.expectedModcount++;
            this.okToRemove = false;
        }

    }
}
