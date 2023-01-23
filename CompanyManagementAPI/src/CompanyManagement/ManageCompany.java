package CompanyManagement;

import AdaptedDataStructures.AdaptedNetwork;
import AdaptedDataStructures.IAdaptedNetwork;
import Locals.Local;
import Locals.ILocal;
import Locals.IMarket;
import Locals.IWarehouse;
import Locals.Market;
import Locals.Warehouse;
import LocalsManagement.IManageMarkets;
import LocalsManagement.IManageWarehouses;
import LocalsManagement.ManageMarkets;
import LocalsManagement.ManageWarehouses;
import RoutesManagement.IManageRoutes;
import RoutesManagement.ManageRoutes;
import SellersManagement.IManageSellers;
import SellersManagement.ISeller;
import SellersManagement.ManageSellers;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Simão
 */
public class ManageCompany implements IManageSellers, IManageMarkets,
        IManageWarehouses, IManageRoutes {

    private final IAdaptedNetwork<ILocal> network;
    private Local company;
    private final ManageMarkets manageMarkets;
    private final ManageWarehouses manageWarehouses;
    private final ManageSellers manageSellers;
    private final ManageRoutes manageRoutes;

    /**
     * Manage Company class constructor.
     *
     * @param name the name of the company
     */
    public ManageCompany(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The company name is invalid.");
        }
        this.company = new Local(name);
        this.network = new AdaptedNetwork<>();
        this.manageMarkets = new ManageMarkets(this.network);
        this.manageWarehouses = new ManageWarehouses(this.network);
        this.manageSellers = new ManageSellers();
        this.manageRoutes = new ManageRoutes(this.network);
        this.network.addVertex(this.company);
    }

    /**
     * Adds a Seller.
     *
     * @param seller the seller to be added
     * @throws IllegalArgumentException if the seller is null
     * @return true if the seller is added otherwise false
     */
    @Override
    public boolean addSeller(ISeller seller) {
        return this.manageSellers.addSeller(seller);
    }

    /**
     * Removes a Seller.
     *
     * @param id the Id of the Seller to be removed
     * @throws IllegalArgumentException if the id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return true if the seller is removed otherwise false
     */
    @Override
    public boolean removeSeller(String id) {
        return this.manageSellers.removeSeller(id);
    }

    /**
     * Getter for the number of Sellers.
     *
     * @return the number of Sellers
     */
    @Override
    public int getNumberOfSellers() {
        return this.manageSellers.getNumberOfSellers();
    }

    /**
     * Getter for the Sellers iterator.
     *
     * @return the Sellers iterator
     */
    @Override
    public Iterator<ISeller> getSellers() {
        return this.manageSellers.getSellers();
    }

    /**
     * Getter for a Seller.
     *
     * @param id the id of the Seller
     * @throws IllegalArgumentException if the id is invalid
     * @return the Seller that has the matching id
     */
    @Override
    public ISeller getSeller(String id) {
        return this.manageSellers.getSeller(id);
    }

    /**
     * Getter for a Seller's name.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller name
     */
    @Override
    public String getSellerName(String id) {
        return this.manageSellers.getSellerName(id);
    }

    /**
     * Setter for a Seller's name.
     *
     * @param name the new name of the Seller
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the name is null or blank
     * @return true if the name is changed otherwise false
     */
    @Override
    public boolean setSellerName(String name, String id) {
        return this.manageSellers.setSellerName(name, id);
    }

    /**
     * Getter for a Seller's max weight.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller max weight, -1 if the Seller doesn't exist
     */
    @Override
    public int getSellerMaxWeight(String id) {
        return this.manageSellers.getSellerMaxWeight(id);
    }

    /**
     * Setter for a Seller's max weight.
     *
     * @param maxWeight the new max weight
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is invalid
     * @throws IllegalArgumentException if the maxWeight is less or equal to
     * zero
     * @throws IllegalStateException if there are no Sellers
     * @return true if the max weight is changed otherwise false
     */
    @Override
    public boolean setSellerMaxWeight(int maxWeight, String id) {
        return this.manageSellers.setSellerMaxWeight(maxWeight, id);
    }

    /**
     * Getter for a Seller's current weight.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller current weight, -1 if the Seller doesn't exist
     */
    @Override
    public int getSellerCurrentWeight(String id) {
        return this.manageSellers.getSellerCurrentWeight(id);
    }

    /**
     * Load Goods to a Seller.
     *
     * @param weight the weight to be loaded
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @throws IllegalStateException if there are no Sellers
     * @return the loaded weight, -1 if the Seller doesn't exist
     */
    @Override
    public int loadGoodsToSeller(int weight, String id) {
        return this.manageSellers.loadGoodsToSeller(weight, id);
    }

    /**
     * Unload Goods from Seller
     *
     * @param weight the weight to be unloaded
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @throws IllegalStateException if there are no Sellers
     * @return the unloaded weight, -1 if the Seller doesn't exist
     */
    @Override
    public int unloadGoodsFromSeller(int weight, String id) {
        return this.manageSellers.unloadGoodsFromSeller(weight, id);
    }

    private boolean marketExists(String name) {
        Iterator<ILocal> localsIterator = this.network.vertices();
        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();
            if (name.equals(currentLocal.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Market to a Seller.
     *
     * @param market the market to be added
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalArgumentException if the market is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return true if the market was added to the Seller otherwise false
     */
    @Override
    public boolean addMarketToSeller(String market, String id) {
        if (this.marketExists(market)) {
            return this.manageSellers.addMarketToSeller(market, id);
        }
        return false;
    }

    /**
     * Removes a Market from a Seller.
     *
     * @param marketName the name of the Market to be removed
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return true if the Market was removed from the Seller otherwise false
     */
    @Override
    public boolean removeMarketFromSeller(String marketName, String id) {
        return this.manageSellers.removeMarketFromSeller(marketName, id);
    }

    /**
     * Getter for the Seller's Markets iterator.
     *
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller's Markets iterator
     */
    @Override
    public Iterator<String> getMarketsFromSeller(String id) {
        return this.manageSellers.getMarketsFromSeller(id);
    }

    /**
     * Prints the object to the console.
     *
     */
    @Override
    public void printSellers() {
        this.manageSellers.printSellers();
    }

    /**
     * Adds a Local.
     *
     * @param local the local to be added
     * @throws IllegalArgumentException if the local is null
     * @return true if the local was added otherwise false
     */
    @Override
    public boolean addLocal(ILocal local) {
        if (local instanceof Market) {
            return this.manageMarkets.addLocal(local);

        } else if (local instanceof Warehouse) {
            return this.manageWarehouses.addLocal(local);
        }
        return false;
    }

    /**
     * Removes a Local.
     *
     * @param name the name of the Local to be removed
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no Locals
     * @return true if the Local was removed otherwise false
     */
    @Override
    public boolean removeLocal(String name) {
        boolean resultM = this.manageMarkets.removeLocal(name);
        boolean resultW = this.manageMarkets.removeLocal(name);
        return !(resultM == false && resultW == false);
    }

    /**
     * Getter for the number of Locals.
     *
     * @return the number of Locals
     */
    @Override
    public int getNumberOfLocals() {
        return this.manageMarkets.getNumberOfLocals();
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printLocals() {
        this.manageMarkets.printLocals();
        this.manageWarehouses.printLocals();
    }

    /**
     * Adds a client to a Market.
     *
     * @param name the name of the market
     * @param neededStock the needed stock by the client
     * @throws IllegalArgumentException if the name is blank or null
     * @throws IllegalArgumentException if the neededStock is less or equal than
     * zero
     * @throws IllegalArgumentException if there are no markets
     * @return true if the client was added otherwise false
     */
    @Override
    public boolean addMarketClient(String name, int neededStock) {
        return this.manageMarkets.addMarketClient(name, neededStock);
    }

    /**
     * Serves a Market's firts client.
     *
     * @param name the name of the market
     * @param stock the served stock
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no markets
     * @throws IllegalStateException if there are no clients on the market
     * @return the stock left to serve, -1 if the market doesn't exist
     */
    @Override
    public int serveMarketClient(String name, int stock) {
        return this.manageMarkets.serveMarketClient(name, stock);
    }

    /**
     * Returns how much stock a Market's first client needs.
     *
     * @param name the name of the market
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no markets
     * @throws IllegalStateException if there are no clients on the market
     * @return the stock, -1 if the market doesn't exist
     */
    @Override
    public int howMuchTheMarketClientNeeds(String name) {
        return this.manageMarkets.howMuchTheMarketClientNeeds(name);
    }

    /**
     * Getter for a Market's number of clients.
     *
     * @param name the name of the market
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no markets
     * @return the number of clients
     */
    @Override
    public int getNumberOfMarketClients(String name) {
        return this.manageMarkets.getNumberOfMarketClients(name);
    }

    /**
     * Getter for the Markets.
     *
     * @return the Markets iterator
     */
    @Override
    public Iterator<IMarket> getMarkets() {
        return this.manageMarkets.getMarkets();
    }

    /**
     * Getter for a Warehouse's capacity.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse capacity, -1 if the warehouse doesn't exist
     */
    @Override
    public int getWarehouseCapacity(String name) {
        return this.manageWarehouses.getWarehouseCapacity(name);
    }

    /**
     * Setter for a Warehouse's capacity.
     *
     * @param name the name of the Warehouse
     * @param capacity the new Warehouse capacity
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the capacity is less or equal than
     * zero
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse capacity
     */
    @Override
    public boolean setWarehouseCapacity(String name, int capacity) {
        return this.manageWarehouses.setWarehouseCapacity(name, capacity);
    }

    /**
     * Getter for a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse stock, -1 if the warehouse doesn't exist
     */
    @Override
    public int getWarehouseStock(String name) {
        return this.manageWarehouses.getWarehouseStock(name);
    }

    /**
     * Loads a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @param stock the stock to be loaded
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no warehouses
     * @return the loaded stock, -1 if the warehouse doesn't exist
     */
    @Override
    public int loadWarehouseStock(String name, int stock) {
        return this.manageWarehouses.loadWarehouseStock(name, stock);
    }

    /**
     * Unloads a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @param stock the stock to be unloaded
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no warehouses
     * @return the unloaded stock, -1 if the warehouse doesn't exist
     */
    @Override
    public int unloadWarehouseStock(String name, int stock) {
        return this.manageWarehouses.unloadWarehouseStock(name, stock);
    }

    /**
     * Getter for the Warehouses.
     *
     * @return the Warehouses iterator
     */
    @Override
    public Iterator<IWarehouse> getWarehouses() {
        return this.manageWarehouses.getWarehouses();
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
        return this.manageRoutes.addRoad(start, destination, distance);
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
        return this.manageRoutes.removeRoad(start, destination);
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

        return this.manageRoutes.setRoadDistance(start, destination, distance);
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
        return this.manageRoutes.generateRouteForSeller(start, seller);
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
        this.manageRoutes.printRouteForSeller(start, seller);
    }

    /**
     * Prints the roads to the console.
     */
    @Override
    public void printRoads() {
        this.manageRoutes.printRoads();
    }

    /**
     * Getter for the name of the company.
     *
     * @return the name of the company
     */
    public String getCompanyName() {
        return this.company.getName();
    }

    /**
     * Setter for the company.
     *
     * @param local
     * @throws IllegalArgumentException if the local is null
     * @return true if the local was changed, otherwise false
     */
    public boolean setCompany(ILocal local) {
        if (local == null) {
            throw new IllegalArgumentException("Invalid name.");
        }
        Iterator<IMarket> markets = this.getMarkets();
        while (markets.hasNext()) {
            IMarket currentMarket = markets.next();
            if (currentMarket.getName().equals(local.getName())) {
                return false;
            }
        }
        Iterator<IWarehouse> warehouses = this.getWarehouses();
        while (warehouses.hasNext()) {
            IWarehouse currentWarehouse = warehouses.next();
            if (currentWarehouse.getName().equals(local.getName())) {
                return false;
            }
        }
        this.network.removeVertex(this.company);
        this.company = (Local) local;
        this.network.addVertex(this.company);
        return true;
    }

    /**
     * Getter for the company.
     *
     * @return the company
     */
    public ILocal getCompany() {
        return this.company;
    }

    private void writeToFile(String path, JSONObject obj) {
        try {
            FileWriter myFile = new FileWriter(path);
            myFile.write(obj.toJSONString());
            myFile.flush();
        } catch (IOException ex) {
            Logger.getLogger(ManageCompany.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    /**
     * Writes the company to a file .json.
     */
    public void companyToJson() {
        JSONObject jCompany = new JSONObject();
        jCompany.put("companyName", this.company.getName());
        JSONArray jMarkets = this.manageMarkets.localsToJson();
        JSONArray jWarehouses = this.manageWarehouses.localsToJson();
        JSONArray jSellers = this.manageSellers.sellersToJson();
        JSONArray jRoads = this.manageRoutes.roadsToJson();
        jCompany.put("markets", jMarkets);
        jCompany.put("warehouses", jWarehouses);
        jCompany.put("sellers", jSellers);
        jCompany.put("roads", jRoads);
        this.writeToFile("./company.json", jCompany);
    }
}
