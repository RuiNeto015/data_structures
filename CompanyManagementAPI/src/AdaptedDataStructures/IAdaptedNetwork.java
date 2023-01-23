package AdaptedDataStructures;

import DataStructuresInterfaces.NetworkADT;
import java.util.Iterator;

/**
 *
 * @author Simão
 * @param <T>
 */
public interface IAdaptedNetwork<T> extends NetworkADT<T> {

    /**
     * Returns the vertices iterator.
     *
     * @return the vertices iterator
     */
    Iterator<T> vertices();

    /**
     * Returns the vertices Iterator with the shortest path between two vertices
     *
     * @param vertex1 the start vertex
     * @param vertex2 the target vertex
     * @return the vertives Iterator
     */
    Iterator<T> getShortestPath(T vertex1, T vertex2);

    /**
     * Returns all the paths.
     *
     * @return the paths iterator
     */
    public Iterator<Path<T>> getPaths();
}
