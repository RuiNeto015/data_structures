package RoutesManagement;

import Locals.ILocal;
import SellersManagement.ISeller;
import java.util.Iterator;

/**
 *
 * @author Simão
 */
public interface IManageRoutes {

    /**
     * Adds a road between two locals.
     *
     * @param start the start local
     * @param destination the distination local
     * @param distance the distance of the road
     * @throws IllegalArgumentException if the start or destination are null or
     * blank
     * @throws IllegalArgumentException if the distance is less or equal than
     * zero
     * @throws IllegalStateException if there are no locations
     * @return true if the road is added, otherwise false
     */
    boolean addRoad(String start, String destination, double distance);

    /**
     * Removes a road between two locals.
     *
     * @param start the start local
     * @param destination the distination local
     * @throws IllegalArgumentException if the start or destination are null or
     * blank
     * @throws IllegalStateException if there are no locations
     * @return true if the road is removed, otherwise false
     */
    boolean removeRoad(String start, String destination);

    /**
     * Sets the road distance between two Locals.
     *
     * @param start the start local
     * @param destination the destination local
     * @param distance the distance between the locals
     * @throws IllegalArgumentException if the start or destination are null or
     * blank
     * @throws IllegalArgumentException if the distance is less or equal than
     * zero
     * @throws IllegalStateException if there are no locations
     * @return true if the distance was changed, otherwise false
     */
    boolean setRoadDistance(String start, String destination, double distance);

    /**
     * Generates the shortest route for the seller considering the markets that
     * he has to visit.
     *
     * @param start the starting point of the route
     * @param seller the seller
     * @throws IllegalArgumentException if the start or seller are null
     * @throws IllegalStateException if there are no locations
     * @return the Locals iterator representing the route
     */
    Iterator<ILocal> generateRouteForSeller(ILocal start, ISeller seller);

    /**
     * Prints the shortest route for the seller considering the markets that he
     * has to visit.
     *
     * @param start the starting point of the route
     * @param seller the seller
     * @throws IllegalArgumentException if the start or seller are null
     * @throws IllegalStateException if there are no locations
     */
    void printRouteForSeller(ILocal start, ISeller seller);

    /**
     * Prints the roads to the console.
     */
    void printRoads();
}
