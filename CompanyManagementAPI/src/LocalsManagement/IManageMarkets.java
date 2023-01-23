package LocalsManagement;

import Locals.IMarket;
import java.util.Iterator;

/**
 *
 * @author Rui Neto
 */
public interface IManageMarkets extends IManageLocals {

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
    boolean addMarketClient(String name, int neededStock);

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
    int serveMarketClient(String name, int stock);

    /**
     * Returns how much stock a Market's first client needs.
     *
     * @param name the name of the market
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no markets
     * @throws IllegalStateException if there are no clients on the market
     * @return the stock, -1 if the market doesn't exist
     */
    int howMuchTheMarketClientNeeds(String name);

    /**
     * Getter for a Market's number of clients.
     *
     * @param name the name of the market
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no markets
     * @return the number of clients
     */
    int getNumberOfMarketClients(String name);

    /**
     * Getter for the Markets.
     *
     * @return the Markets iterator
     */
    Iterator<IMarket> getMarkets();
}
