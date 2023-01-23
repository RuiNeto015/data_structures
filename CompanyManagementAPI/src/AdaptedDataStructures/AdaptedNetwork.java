package AdaptedDataStructures;

import DataStructures.LinkedUnorderedList;
import DataStructures.Network;
import DataStructuresInterfaces.UnorderedListADT;
import java.util.Iterator;

/**
 *
 * @author Simão
 * @param <T>
 */
public class AdaptedNetwork<T> extends Network<T> implements IAdaptedNetwork<T> {

    /**
     * Returns the vertices iterator.
     *
     * @return the vertices iterator
     */
    @Override
    public Iterator<T> vertices() {
        UnorderedListADT<T> unorderedList = new LinkedUnorderedList<>();
        for (int i = 0; i < super.numVertices; i++) {
            unorderedList.addToRear(super.vertices[i]);
        }
        return unorderedList.iterator();
    }

    /**
     * Returns the vertices Iterator with the shortest path between two vertices
     *
     * @param vertex1 the start vertex
     * @param vertex2 the target vertex
     * @return the vertives Iterator
     */
    @Override
    public Iterator<T> getShortestPath(T vertex1, T vertex2) {
        int index1 = super.findVertex(vertex1);
        int index2 = super.findVertex(vertex2);
        UnorderedListADT<T> path = new LinkedUnorderedList<>();

        if (index1 < 0 || index2 < 0) {
            return path.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(index1, index2);
        while (it.hasNext()) {
            int currentIndex = it.next();
            path.addToRear(super.vertices[currentIndex]);
        }
        return path.iterator();
    }

    /**
     * Returns all the paths.
     *
     * @return the paths iterator
     */
    @Override
    public Iterator<Path<T>> getPaths() {
        UnorderedListADT<Path<T>> paths = new LinkedUnorderedList<>();
        for (int i = 0; i < super.numVertices; i++) {
            for (int j = i; j < super.numVertices; j++) {
                if (this.weights[i][j] != Double.POSITIVE_INFINITY) {
                    paths.addToRear(new Path<>(super.vertices[i],
                            super.vertices[j], this.weights[i][j]));
                }
            }
        }
        return paths.iterator();
    }
}
