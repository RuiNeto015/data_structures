package SellersManagement;

import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.UnorderedListADT;
import java.util.Iterator;
import org.json.simple.JSONArray;

/**
 *
 * @author Simão
 */
public class ManageSellers implements IManageSellers {

    private UnorderedListADT<ISeller> sellers;

    /**
     * ManageSellers class constructor.
     *
     */
    public ManageSellers() {
        this.sellers = new LinkedUnorderedList<>();
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
        if (seller == null) {
            throw new IllegalArgumentException("Seller is null.");
        }
        if (this.getSeller(seller.getId()) != null) {
            return false;
        }
        this.sellers.addToRear(seller);
        return true;
    }

    private void checkIfArgumentAndStateAreValids(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is invalid.");
        }
        if (this.sellers.isEmpty()) {
            throw new IllegalStateException("There are no Sellers.");
        }
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return false;
        }
        this.sellers.remove(seller);
        return true;
    }

    /**
     * Getter for the number of Sellers.
     *
     * @return the number of Sellers
     */
    @Override
    public int getNumberOfSellers() {
        return this.sellers.size();
    }

    /**
     * Getter for the Sellers iterator.
     *
     * @return the Sellers iterator
     */
    @Override
    public Iterator<ISeller> getSellers() {
        return this.sellers.iterator();
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
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("The id is invalid.");
        }
        Iterator<ISeller> sellersIterator = this.sellers.iterator();

        while (sellersIterator.hasNext()) {
            ISeller currentSeller = sellersIterator.next();
            if (currentSeller.getId().equals(id)) {
                return currentSeller;
            }
        }
        return null;
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return null;
        }
        return seller.getName();
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return false;
        }
        seller.setName(name);
        return true;
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return -1;
        }
        return seller.getMaxWeight();
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return false;
        }
        seller.setMaxWeight(maxWeight);
        return true;
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return -1;
        }
        return seller.getCurrentWeight();
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return -1;
        }
        return seller.loadGoods(weight);
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return -1;
        }
        return seller.unloadGoods(weight);
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return false;
        }
        return seller.addMarketToVisit(market);
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return false;
        }
        return seller.removeMarketToVisit(marketName);
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
        this.checkIfArgumentAndStateAreValids(id);
        ISeller seller = this.getSeller(id);

        if (seller == null) {
            return null;
        }
        return seller.getMarketsToVisit();
    }

    /**
     * Prints the object to the console.
     *
     */
    @Override
    public void printSellers() {
        System.out.println("*********************");
        System.out.println("-------Sellers-------");
        System.out.println("*********************");
        System.out.println("Number of Sellers: " + this.getNumberOfSellers());
        Iterator<ISeller> sellersIterator = this.sellers.iterator();

        while (sellersIterator.hasNext()) {
            ISeller currentSeller = sellersIterator.next();
            currentSeller.printObject();
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON array
     */
    public JSONArray sellersToJson() {
        JSONArray jSellers = new JSONArray();
        Iterator<ISeller> sellersIterator = this.sellers.iterator();

        while (sellersIterator.hasNext()) {
            ISeller currentSeller = sellersIterator.next();
            jSellers.add(currentSeller.sellerToJson());
        }
        return jSellers;
    }

}
