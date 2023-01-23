package DataStructures;

import DataStructuresInterfaces.QueueADT;

/**
 *
 * @author Simão
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private Node<T> front;
    private Node<T> rear;
    private int count;

    public LinkedQueue() {
        this.count = 0;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        Node<T> node = new Node<>(element);

        if (this.isEmpty()) {
            this.front = node;
            this.rear = this.front;
        } else {
            Node<T> tmp = new Node<>(element);
            this.rear.setNext(tmp);
            this.rear = this.rear.getNext();
        }
        this.count++;
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }

        if (this.count == 1) {
            T removed = this.front.getData();
            this.front = null;
            this.rear = null;
            this.count = 0;
            return removed;
        }
        T oldFirst = this.front.getData();
        this.front = this.front.getNext();
        this.count--;
        return oldFirst;
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     */
    @Override
    public T first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.front.getData();
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString() {
        if(this.isEmpty()){
            return null;
        }
        
        String tmp = "";
        Node<T> n = this.front;

        while (n.getNext() != null) {
            tmp += n.getData() + "; ";
            n = n.getNext();
        }
        tmp += n.getData();
        return tmp;
    }
}
