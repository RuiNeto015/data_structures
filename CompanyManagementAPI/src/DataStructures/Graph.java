package DataStructures;

import DataStructuresInterfaces.GraphADT;
import java.util.Iterator;

/**
 *
 * @author Simão
 * @param <T>
 */
public class Graph<T> implements GraphADT<T> {

    /**
     * Default capacity for the adjMatrix and vertices.
     */
    protected final int DEFAULT_CAPACITY = 3;

    /**
     * The vertices counter.
     */
    protected int numVertices;

    /**
     * the adjMatrix.
     */
    protected boolean[][] adjMatrix;

    /**
     * the array that stores the vertices.
     */
    protected T[] vertices;

    /**
     * Graph class constructor.
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    private void expandVertices() {
        T[] temp = (T[]) (new Object[this.vertices.length]);
        System.arraycopy(this.vertices, 0, temp, 0, this.vertices.length);
        this.vertices = (T[]) (new Object[this.vertices.length * 2]);
        System.arraycopy(temp, 0, this.vertices, 0, temp.length);
    }

    private void expandCapacity() {
        int oldLenght = this.vertices.length;
        int newLenght = this.vertices.length * 2;
        this.expandVertices();
        boolean[][] temp = new boolean[newLenght][newLenght];

        for (int i = 0; i < oldLenght; i++) {
            System.arraycopy(this.adjMatrix[i], 0, temp[i], 0, oldLenght);
        }
        this.adjMatrix = temp;
    }

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex the vertex to be added to this graph
     */
    @Override
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length) {
            expandCapacity();
        }
        this.vertices[this.numVertices] = vertex;
        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }
        this.numVertices++;
    }

    /**
     * Returns the vertex index
     *
     * @param vertex the vertex
     * @return the vertex index
     */
    protected int findVertex(T vertex) {
        int found = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i].equals(vertex)) {
                found = i;
                break;
            }
        }
        return found;
    }

    private void shiftArray(int index) {
        if (index == this.vertices.length - 1) {
            this.vertices[index] = null;
            return;
        }

        for (int i = index; i < this.vertices.length - 1; i++) {
            this.vertices[i] = this.vertices[i + 1];
        }
    }

    private void shiftMatrix(int index) {
        for (int i = index; i < this.numVertices; i++) {
            for (int j = 0; j <= this.numVertices; j++) {
                this.adjMatrix[i][j] = this.adjMatrix[i + 1][j];
            }
        }
        for (int i = index; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjMatrix[j][i] = this.adjMatrix[j][i + 1];
            }
        }
    }

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {
        if (this.isEmpty()) {
            return;
        }
        int index = this.findVertex(vertex);
        if (index < 0) {
            return;
        }
        this.shiftArray(index);
        this.numVertices--;
        this.shiftMatrix(index);
    }

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        if (this.isEmpty()) {
            return;
        }
        int index1 = this.findVertex(vertex1);
        int index2 = this.findVertex(vertex2);

        if (index1 >= 0 && index2 >= 0) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        if (this.isEmpty()) {
            return;
        }
        int index1 = this.findVertex(vertex1);
        int index2 = this.findVertex(vertex2);

        if (index1 >= 0 && index2 >= 0) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Returns a breadth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a breadth first iterator beginning at the given vertex
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        int x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        LinkedUnorderedList<T> resultList = new LinkedUnorderedList<>();
        boolean[] visited = new boolean[this.numVertices];
        int startIndex = this.findVertex(startVertex);

        if (startIndex < 0) {
            return resultList.iterator();
        }
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(this.vertices[x]);
            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns a depth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a depth first iterator starting at the given vertex
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        int x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        LinkedUnorderedList<T> resultList = new LinkedUnorderedList<>();
        boolean[] visited = new boolean[this.numVertices];
        int startIndex = this.findVertex(startVertex);

        if (startIndex < 0) {
            return resultList.iterator();
        }
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(this.vertices[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            for (int i = 0; (i < this.numVertices) && !found; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * @param startVertex the starting vertex
     * @param targetVertex the ending vertex
     * @return an iterator that contains the shortest path between the two
     * vertices
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        throw new IllegalStateException("Not implemented ");
    }

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return true if this graph is empty
     */
    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    /**
     * Returns true if this graph is connected, false otherwise.
     *
     * @return true if this graph is connected
     */
    @Override
    public boolean isConnected() {
        if (this.isEmpty()) {
            return false;
        }
        Iterator<T> it = iteratorBFS(this.vertices[0]);
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count == numVertices;
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the integer number of vertices in this graph
     */
    @Override
    public int size() {
        return this.numVertices;
    }

}
