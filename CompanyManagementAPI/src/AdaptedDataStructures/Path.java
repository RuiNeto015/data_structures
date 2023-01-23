package AdaptedDataStructures;

/**
 *
 * @author Simão
 * @param <T>
 */
public class Path<T> {
    
    private T start;
    private T destination;
    private double weight;
    
    /**
     * Path class constructor.
     * 
     * @param start the start of the path
     * @param destination the destination of the path
     * @param weight the weight between the path
     */
    public Path(T start, T destination, double weight){
        this.start = start;
        this.destination = destination;
        this.weight = weight;
    }
    
    /**
     * The start of the path.
     * 
     * @return the start of the path
     */
    public T getStart(){
        return this.start;
    }
    
    /**
     * The destination of the path.
     * 
     * @return the destination of the path
     */
    public T getDestination(){
        return this.destination;
    }
    
    /**
     * The weight of the path.
     * 
     * @return the weight of the path
     */
    public double getWeight(){
        return this.weight;
    }
}
