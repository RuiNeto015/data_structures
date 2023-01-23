package SellersManagement;

import java.util.Iterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Simão
 */
public interface ISeller {

    /**
     * Getter for the Seller Id.
     *
     * @return the Seller Id
     */
    String getId();

    /**
     * Getter for the Seller Name.
     *
     * @return the Seller Name
     */
    String getName();

    /**
     * Setter for the Seller Name.
     *
     * @param name the Seller name
     * @throws IllegalArgumentException if the name is null or blank
     */
    void setName(String name);

    /**
     * Getter for the Seller Max Weight.
     *
     * @return the Seller Max Weight
     */
    int getMaxWeight();

    /**
     * Setter for the Seller Max Weight.
     *
     * @param maxWeight the Seller Max Weight
     * @throws IllegalArgumentException if the maxWeight is less or equal to
     * zero
     */
    void setMaxWeight(int maxWeight);

    /**
     * Getter for the Seller Current Weight
     *
     * @return the Seller Current Weight
     */
    int getCurrentWeight();

    /**
     * Loads the Seller.
     *
     * @param weight the weight to be loaded
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @return the loaded weight
     */
    int loadGoods(int weight);

    /**
     * Unloads the Seller.
     *
     * @param weight the weight to be unloaded
     * @throws IllegalArgumentException if the weight is less or equal to zero
     * @return the unloaded weight
     */
    int unloadGoods(int weight);

    /**
     * Adds a Market to the Seller.
     *
     * @param market the Market to be added
     * @throws IllegalArgumentException if the market is null or blank
     * @return true if the market was added otherwise false
     */
    boolean addMarketToVisit(String market);

    /**
     * Removes a Market from the Seller.
     *
     * @param name the Name of the Market to be removed
     * @throws IllegalArgumentException if the name is null or blank
     * @return true if the market was removed otherwise false
     */
    boolean removeMarketToVisit(String name);

    /**
     * Getter for the Markets that the Seller has to visit.
     *
     * @return the Markets that the Seller has to visit
     */
    Iterator<String> getMarketsToVisit();

    /**
     * Prints the object to the console.
     *
     */
    void printObject();

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    JSONObject sellerToJson();
}
