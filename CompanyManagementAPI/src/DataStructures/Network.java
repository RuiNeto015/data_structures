package DataStructures;

import DataStructuresInterfaces.NetworkADT;
import DataStructuresInterfaces.OrderedListADT;
import DataStructuresInterfaces.UnorderedListADT;
import java.util.Iterator;

/**
 *
 * @author Simão
 * @param <T>
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    /**
     * the weights matrix.
     */
    protected double[][] weights;

    /**
     * Network class constructor.
     */
    public Network() {
        super();
        this.weights = new double[super.DEFAULT_CAPACITY][super.DEFAULT_CAPACITY];
    }

    private void setEdgeWeight(T vertex1, T vertex2, double weight) {
        int index1 = super.findVertex(vertex1);
        int index2 = super.findVertex(vertex2);
        if (index1 >= 0 || index2 >= 0) {
            this.weights[index1][index2] = weight;
            this.weights[index2][index1] = weight;
        }
    }

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be > 0");
        }
        super.addEdge(vertex1, vertex2);
        this.setEdgeWeight(vertex1, vertex2, weight);
    }

    private void expandCapacity() {
        int oldLenght = super.vertices.length;
        int newLenght = super.vertices.length * 2;
        double[][] temp = new double[newLenght][newLenght];

        for (int i = 0; i < oldLenght; i++) {
            System.arraycopy(this.weights[i], 0, temp[i], 0, oldLenght);
        }
        this.weights = temp;
    }

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        if (super.isEmpty()) {
            return;
        }
        int index1 = super.findVertex(vertex1);
        int index2 = super.findVertex(vertex2);

        if (index1 >= 0 && index2 >= 0) {
            this.weights[index1][index2] = Double.POSITIVE_INFINITY;
            this.weights[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex the vertex to be added to this graph
     */
    @Override
    public void addVertex(T vertex) {
        if (super.numVertices == super.vertices.length) {
            expandCapacity();
        }
        super.addVertex(vertex);
        for (int i = 0; i < super.numVertices; i++) {
            this.weights[super.numVertices - 1][i] = Double.POSITIVE_INFINITY;
            this.weights[i][super.numVertices - 1] = Double.POSITIVE_INFINITY;
        }
    }

    private void shiftMatrix(int index) {
        for (int i = index; i < super.numVertices; i++) {
            for (int j = 0; j <= super.numVertices; j++) {
                this.weights[i][j] = this.weights[i + 1][j];
            }
        }
        for (int i = index; i < super.numVertices; i++) {
            for (int j = 0; j < super.numVertices; j++) {
                this.weights[j][i] = this.weights[j][i + 1];
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
        int index = this.findVertex(vertex);
        if (index < 0) {
            return;
        }
        super.removeVertex(vertex);
        this.shiftMatrix(index);
    }

    /**
     * Returns the index of the the vertex that that is adjacent to the vertex
     * with the given index and also has a pathWeight equal to weight.
     *
     * @param visited the visited vertices
     * @param pathWeight the weight to the vertices
     * @param weight the weight
     * @return the index
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {

        for (int i = 0; i < super.numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < super.numVertices; j++) {
                    if ((this.weights[i][j] < Double.POSITIVE_INFINITY)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.
     *
     * @param startIndex the start index
     * @param targetIndex the target index
     * @return the path iterator
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex,
            int targetIndex) {

        int index;
        double weight;
        int[] predecessor = new int[super.numVertices];
        OrderedListADT<Double> orderedList = new LinkedOrderedList<>();
        UnorderedListADT<Integer> resultList = new LinkedUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        double[] pathWeight = new double[super.numVertices];
        for (int i = 0; i < super.numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[super.numVertices];
        for (int i = 0; i < super.numVertices; i++) {
            visited[i] = false;
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        for (int i = 0; i < super.numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex]
                        + this.weights[startIndex][i];
                predecessor[i] = startIndex;
                orderedList.add(pathWeight[i]);
            }
        }

        do {
            weight = orderedList.removeFirst();
            orderedList = new LinkedOrderedList<>();
            if (weight == Double.POSITIVE_INFINITY) {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            for (int i = 0; i < super.numVertices; i++) {
                if (!visited[i]) {

                    if ((this.weights[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + this.weights[index][i])
                            < pathWeight[i]) {

                        pathWeight[i] = pathWeight[index]
                                + this.weights[index][i];

                        predecessor[i] = index;
                    }
                    orderedList.add(pathWeight[i]);
                }
            }
        } while (!orderedList.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);

        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }
        return resultList.iterator();
    }

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        double result = 0;
        int index1 = super.findVertex(vertex1);
        int index2 = super.findVertex(vertex2);

        if (index1 < 0 || index2 < 0) {
            return -1;
        }

        Iterator<Integer> it = iteratorShortestPathIndices(index1, index2);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = it.next();
            result += this.weights[index1][index2];
            index1 = index2;
        }
        return result;
    }
}
