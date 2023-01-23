package SellersManagement;

import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.UnorderedListADT;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Simão
 */
public class Seller implements ISeller {

    private final String id;
    private String name;
    private int maxWeight;
    private int currentWeight;
    private final UnorderedListADT<String> marketsToVisit;

    /**
     * Seller class constructor.
     *
     * @param id the Seller Id
     * @param name the Seller name
     * @param maxWeight the Seller Max Weight
     * @throws IllegalArgumentException if the id is null or blank
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the maxWeight is less or equal to
     * zero
     */
    public Seller(String id, String name, int maxWeight) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is invalid.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is invalid.");
        }
        if (maxWeight <= 0) {
            throw new IllegalArgumentException("Weight is invalid.");
        }
        this.id = id;
        this.name = name;
        this.maxWeight = maxWeight;
        this.marketsToVisit = new LinkedUnorderedList<>();
    }

    /**
     * Getter for the Seller Id.
     *
     * @return the Seller Id
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Getter for the Seller Name.
     *
     * @return the Seller Name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the Seller Name.
     *
     * @param name the Seller name
     * @throws IllegalArgumentException if the name is null or blank
     */
    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is invalid.");
        }
        this.name = name;
    }

    /**
     * Getter for the Seller Max Weight.
     *
     * @return the Seller Max Weight
     */
    @Override
    public int getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * Setter for the Seller Max Weight.
     *
     * @param maxWeight the Seller Max Weight
     * @throws IllegalArgumentException if the maxWeight is less or equal to
     * zero
     */
    @Override
    public void setMaxWeight(int maxWeight) {
        if (maxWeight <= 0) {
            throw new IllegalArgumentException("Weight is invalid.");
        }
        this.maxWeight = maxWeight;
    }

    /**
     * Getter for the Seller Current Weight
     *
     * @return the Seller Current Weight
     */
    @Override
    public int getCurrentWeight() {
        return this.currentWeight;
    }

    /**
     * Loads the Seller.
     *
     * @param weight the weight to be loaded
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @return the loaded weight
     */
    @Override
    public int loadGoods(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight is invalid.");
        }

        int leftSpace = this.maxWeight - this.currentWeight;
        if (weight >= leftSpace) {
            this.currentWeight += leftSpace;
            return leftSpace;
        }
        this.currentWeight += weight;
        return weight;
    }

    /**
     * Unloads the Seller.
     *
     * @param weight the weight to be unloaded
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @return the unloaded weight
     */
    @Override
    public int unloadGoods(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight is invalid.");
        }
        if (this.currentWeight <= weight) {
            int unloaded = this.currentWeight;
            this.currentWeight = 0;
            return unloaded;
        }
        this.currentWeight -= weight;
        return weight;
    }

    private String getMarket(String name) {
        Iterator<String> marketsToVisitIterator = this.marketsToVisit.iterator();
        while (marketsToVisitIterator.hasNext()) {
            String currentMarket = marketsToVisitIterator.next();
            if (currentMarket.equals(name)) {
                return currentMarket;
            }
        }
        return null;
    }

    /**
     * Adds a Market to the Seller.
     *
     * @param market the Market to be added
     * @throws IllegalArgumentException if the market is null or blank
     * @return true if the market was added otherwise false
     */
    @Override
    public boolean addMarketToVisit(String market) {
        if (market == null || market.isBlank()) {
            throw new IllegalArgumentException("market is null.");
        }
        if (this.getMarket(market) != null) {
            return false;
        }
        this.marketsToVisit.addToRear(market);
        return true;
    }

    /**
     * Removes a Market from the Seller.
     *
     * @param name the Name of the Market to be removed
     * @throws IllegalArgumentException if the name is null or blank
     * @return true if the market was removed otherwise false
     */
    @Override
    public boolean removeMarketToVisit(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is invalid.");
        }
        if (this.marketsToVisit.isEmpty()) {
            return false;
        }
        return this.marketsToVisit.remove(this.getMarket(name)) != null;
    }

    /**
     * Getter for the Markets that the Seller has to visit.
     *
     * @return the Markets that the Seller has to visit
     */
    @Override
    public Iterator<String> getMarketsToVisit() {
        return this.marketsToVisit.iterator();
    }

    /**
     * Prints the object to the console.
     *
     */
    @Override
    public void printObject() {
        System.out.println("--------Seller-------");
        System.out.println("Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Capacity: " + this.maxWeight);
        System.out.println("Markets to visit:");
        Iterator<String> marketsToVisitIterator = this.marketsToVisit.iterator();
        while (marketsToVisitIterator.hasNext()) {
            String market = marketsToVisitIterator.next();
            System.out.println("-" + market);
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    @Override
    public JSONObject sellerToJson() {
        JSONObject jSeller = new JSONObject();
        jSeller.put("id", this.id);
        jSeller.put("name", this.name);
        jSeller.put("maxWeight", this.maxWeight);
        JSONArray jMarketsToVisit = new JSONArray();
        Iterator<String> marketsToVisitIterator = this.marketsToVisit.iterator();

        while (marketsToVisitIterator.hasNext()) {
            String currentMarket = marketsToVisitIterator.next();
            jMarketsToVisit.add(currentMarket);
        }
        jSeller.put("markets", jMarketsToVisit);
        return jSeller;
    }
}
