package DataStructures;

import DataStructuresInterfaces.StackADT;

/**
 *
 * @author Simão
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T> {

    private Node<T> top;
    private int count;

    /**
     * Constructor for the LinkedStack Class.
     */
    public LinkedStack() {
        this.count = 0;
    }

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        Node<T> node = new Node<>(element);

        if (this.top == null) {
            this.top = node;
        } else {
            Node<T> oldTop = this.top;
            this.top = node;
            this.top.setNext(oldTop);
        }
        this.count++;
    }

    /**
     * Removes and returns the top element from this stack.
     *
     * @return T element removed from the top of the stack
     */
    @Override
    public T pop() {
        if (!this.isEmpty()) {
            Node<T> tmp = this.top;
            this.top = this.top.getNext();
            this.count--;
            return tmp.getData();
        }
        return null;
    }

    /**
     * Returns without removing the top element of this stack.
     *
     * @return T element on top of the stack
     */
    @Override
    public T peek() {
        if (!this.isEmpty()) {
            return this.top.getData();
        }
        return null;
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return String representation of this stack
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return null;
        }
        
        String str = "";
        Node<T> n = this.top;

        while (n.getNext() != null) {
            str += n.getData() + "; ";
            n = n.getNext();
        }
        str += n.getData();
        return str;
    }
}
