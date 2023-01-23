package SellersManagement;

import java.util.Iterator;

/**
 *
 * @author Simão
 */
public interface IManageSellers {

    /**
     * Adds a Seller.
     *
     * @param seller the seller to be added
     * @throws IllegalArgumentException if the seller is null
     * @return true if the seller is added otherwise false
     */
    boolean addSeller(ISeller seller);

    /**
     * Removes a Seller.
     *
     * @param id the Id of the Seller to be removed
     * @throws IllegalArgumentException if the id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return true if the seller is removed otherwise false
     */
    boolean removeSeller(String id);

    /**
     * Getter for the number of Sellers.
     *
     * @return the number of Sellers
     */
    int getNumberOfSellers();

    /**
     * Getter for the Sellers iterator.
     *
     * @return the Sellers iterator
     */
    Iterator<ISeller> getSellers();

    /**
     * Getter for a Seller.
     *
     * @param id the id of the Seller
     * @throws IllegalArgumentException if the id is invalid
     * @return the Seller that has the matching id
     */
    ISeller getSeller(String id);

    /**
     * Getter for a Seller's name.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller name
     */
    String getSellerName(String id);

    /**
     * Setter for a Seller's name.
     *
     * @param name the new name of the Seller
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the name is null or blank
     * @return true if the name is changed otherwise false
     */
    boolean setSellerName(String name, String id);

    /**
     * Getter for a Seller's max weight.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller max weight, -1 if the Seller doesn't exist
     */
    int getSellerMaxWeight(String id);

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
    boolean setSellerMaxWeight(int maxWeight, String id);

    /**
     * Getter for a Seller's current weight.
     *
     * @param id the Id of the Seller
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller current weight, -1 if the Seller doesn't exist
     */
    int getSellerCurrentWeight(String id);

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
    int loadGoodsToSeller(int weight, String id);

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
    int unloadGoodsFromSeller(int weight, String id);

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
    boolean addMarketToSeller(String market, String id);

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
    boolean removeMarketFromSeller(String marketName, String id);

    /**
     * Getter for the Seller's Markets iterator.
     *
     * @param id the Seller Id
     * @throws IllegalArgumentException if the Id is null or blank
     * @throws IllegalStateException if there are no Sellers
     * @return the Seller's Markets iterator
     */
    Iterator<String> getMarketsFromSeller(String id);

    /**
     * Prints the object to the console.
     *
     */
    void printSellers();
}
