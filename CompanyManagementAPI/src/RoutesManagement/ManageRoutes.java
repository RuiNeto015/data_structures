package RoutesManagement;

import AdaptedDataStructures.IAdaptedNetwork;
import AdaptedDataStructures.Path;
import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.UnorderedListADT;
import Locals.ILocal;
import Locals.IMarket;
import Locals.IWarehouse;
import Locals.Market;
import Locals.Warehouse;
import SellersManagement.ISeller;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Simão
 */
public class ManageRoutes implements IManageRoutes {

    private IAdaptedNetwork<ILocal> network;

    /**
     * ManageRoutes class constructor.
     *
     * @param network the network
     * @throws IllegalArgumentException if the network is null
     */
    public ManageRoutes(IAdaptedNetwork<ILocal> network) {
        this.network = network;
    }

    private ILocal getLocal(String name) {
        Iterator<ILocal> localsIterator = this.network.vertices();

        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();

            if (currentLocal.getName().equals(name)) {
                return currentLocal;
            }
        }
        return null;
    }

    private void checkIfArgumentsAreValid(String start, String destination) {
        if (this.network.isEmpty()) {
            throw new IllegalStateException("There are no locations.");
        }
        if (start == null || start.isBlank()) {
            throw new IllegalArgumentException("Start is invalid.");
        }
        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Destination is invalid.");
        }
    }

    private boolean roadExists(ILocal startLocal, ILocal destinationLocal) {
        Iterator<Path<ILocal>> roads = this.network.getPaths();

        while (roads.hasNext()) {
            Path<ILocal> currentRoad = roads.next();

            if (currentRoad.getStart().equals(startLocal)
                    && currentRoad.getDestination().equals(destinationLocal)) {

                return true;
            }
        }
        return false;
    }

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
    @Override
    public boolean addRoad(String start, String destination, double distance) {
        this.checkIfArgumentsAreValid(start, destination);
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be > 0.");
        }
        ILocal startLocal = this.getLocal(start);
        ILocal destinationLocal = this.getLocal(destination);

        if (startLocal == null || destinationLocal == null
                || this.roadExists(startLocal, destinationLocal)) {

            return false;
        }
        this.network.addEdge(startLocal, destinationLocal, distance);
        return true;
    }

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
    @Override
    public boolean removeRoad(String start, String destination) {
        this.checkIfArgumentsAreValid(start, destination);
        ILocal startLocal = this.getLocal(start);
        ILocal destinationLocal = this.getLocal(destination);

        if (startLocal == null || destinationLocal == null
                || !this.roadExists(startLocal, destinationLocal)) {

            return false;
        }
        this.network.removeEdge(startLocal, destinationLocal);
        return true;
    }

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
    @Override
    public boolean setRoadDistance(String start, String destination,
            double distance) {

        this.checkIfArgumentsAreValid(start, destination);
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be > 0.");
        }
        ILocal startLocal = this.getLocal(start);
        ILocal destinationLocal = this.getLocal(destination);

        if (startLocal == null || destinationLocal == null
                || !this.roadExists(startLocal, destinationLocal)) {

            return false;
        }
        this.network.addEdge(startLocal, destinationLocal, distance);
        return true;
    }

    private void loadSeller(ISeller seller, IWarehouse warehouse) {
        int sellerFreeSpace = seller.getMaxWeight() - seller.getCurrentWeight();
        seller.loadGoods(warehouse.unloadStock(sellerFreeSpace));
    }

    private Iterator<IWarehouse> getWarehouses() {
        Iterator<ILocal> localsIterator = this.network.vertices();
        UnorderedListADT<IWarehouse> warehouses = new LinkedUnorderedList<>();

        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();

            if (currentLocal instanceof Warehouse) {
                IWarehouse warehouse = (IWarehouse) currentLocal;

                if (warehouse.getStock() > 0) {
                    warehouses.addToRear(warehouse);
                }
            }
        }
        return warehouses.iterator();
    }

    private IWarehouse visitWarehouse(ILocal market, ISeller seller) {
        IWarehouse minWarehouse = null;
        Iterator<IWarehouse> warehousesIt = this.getWarehouses();

        if (warehousesIt.hasNext()) {
            minWarehouse = warehousesIt.next();
            double min = this.network.shortestPathWeight(market, minWarehouse);

            while (warehousesIt.hasNext()) {
                IWarehouse currentWarehouse = warehousesIt.next();
                double toCompare = this.network.shortestPathWeight(market,
                        currentWarehouse);

                if (min > toCompare) {
                    min = toCompare;
                    minWarehouse = currentWarehouse;
                }
            }
        }
        if (minWarehouse != null) {
            this.loadSeller(seller, minWarehouse);
        }
        return minWarehouse;
    }

    private void addLocalsToRoute(UnorderedListADT<ILocal> route,
            Iterator<ILocal> it) {

        if (it.hasNext()) {
            it.next();
        }
        while (it.hasNext()) {
            ILocal currentLocal = it.next();
            route.addToRear(currentLocal);
        }
    }

    private Iterator<IMarket> getMarketsFromSeller(Iterator<String> markets) {
        UnorderedListADT<IMarket> marketsList = new LinkedUnorderedList<>();

        while (markets.hasNext()) {
            String currentMarket = markets.next();
            ILocal local = this.getLocal(currentMarket);

            if (local instanceof Market market) {
                marketsList.addToRear(market);
            } else {
                return null;
            }
        }
        return marketsList.iterator();
    }

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
    @Override
    public Iterator<ILocal> generateRouteForSeller(ILocal start, ISeller seller) {
        if (start == null || seller == null) {
            throw new IllegalArgumentException("Null arguments are invalid.");
        }
        if (this.network.isEmpty()) {
            throw new IllegalStateException("There are no Locations.");
        }
        UnorderedListADT<ILocal> route = new LinkedUnorderedList<>();
        Iterator<IMarket> marketsIterator = this.getMarketsFromSeller(seller.
                getMarketsToVisit());

        ILocal currentLocal = start;

        if (this.getLocal(start.getName()) == null || marketsIterator == null) {
            return route.iterator();
        }

        while (marketsIterator.hasNext()) {
            IMarket marketToGo = marketsIterator.next();

            if (marketToGo.getNumberOfClients() != 0) {
                Iterator<ILocal> it = this.network.getShortestPath(currentLocal,
                        marketToGo);

                this.addLocalsToRoute(route, it);
                currentLocal = marketToGo;
            }
            while (marketToGo.getNumberOfClients() != 0) {
                int clientNeeds = marketToGo.HowMuchTheClientNeeds();
                int unloadFromSeller = seller.unloadGoods(clientNeeds);

                while (marketToGo.serveClient(unloadFromSeller) > 0) {
                    IWarehouse warehouse = this.visitWarehouse(currentLocal,
                            seller);

                    if (warehouse != null) {
                        Iterator<ILocal> it = this.network.getShortestPath(
                                currentLocal, warehouse);

                        this.addLocalsToRoute(route, it);
                        it = this.network.getShortestPath(warehouse,
                                currentLocal);

                        this.addLocalsToRoute(route, it);
                        clientNeeds = marketToGo.HowMuchTheClientNeeds();
                        unloadFromSeller = seller.unloadGoods(clientNeeds);
                    } else {
                        return route.iterator();
                    }
                }
            }
        }
        return route.iterator();
    }

    /**
     * Prints the shortest route for the seller considering the markets that he
     * has to visit.
     *
     * @param start the starting point of the route
     * @param seller the seller
     * @throws IllegalArgumentException if the start or seller are null
     * @throws IllegalStateException if there are no locations
     */
    @Override
    public void printRouteForSeller(ILocal start, ISeller seller) {
        Iterator<ILocal> locals = this.generateRouteForSeller(start, seller);
        System.out.println("*********************");
        System.out.println("---------Route-------");
        System.out.println("*********************");
        int i = 1;
        while (locals.hasNext()) {
            ILocal currentLocal = locals.next();
            System.out.println(i++ + ": " + currentLocal.getName());
        }
    }

    /**
     * Prints the roads to the console.
     */
    @Override
    public void printRoads() {
        Iterator<Path<ILocal>> roads = this.network.getPaths();
        System.out.println("*********************");
        System.out.println("--------Roads--------");
        System.out.println("*********************");

        while (roads.hasNext()) {
            Path<ILocal> current = roads.next();
            System.out.println("From " + current.getStart().getName()
                    + " to " + current.getDestination().getName()
                    + ": " + current.getWeight());
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON array
     */
    public JSONArray roadsToJson() {
        JSONArray jRoads = new JSONArray();
        Iterator<Path<ILocal>> roads = this.network.getPaths();

        while (roads.hasNext()) {
            Path<ILocal> currentRoad = roads.next();
            JSONObject jRoad = new JSONObject();
            jRoad.put("from", currentRoad.getStart().getName());
            jRoad.put("to", currentRoad.getDestination().getName());
            jRoad.put("distance", currentRoad.getWeight());
            jRoads.add(jRoad);
        }
        return jRoads;
    }
}
