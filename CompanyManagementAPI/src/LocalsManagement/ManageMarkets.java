package LocalsManagement;

import AdaptedDataStructures.IAdaptedNetwork;
import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.UnorderedListADT;
import Locals.ILocal;
import Locals.IMarket;
import Locals.Market;
import java.util.Iterator;
import org.json.simple.JSONArray;

/**
 *
 * @author Rui Neto
 */
public class ManageMarkets extends ManageLocals implements IManageMarkets {

    private int counter;

    /**
     * ManageMarkets class constructor.
     *
     * @param network the network
     * @throws IllegalArgumentException if the network is null or not empty
     */
    public ManageMarkets(IAdaptedNetwork<ILocal> network) {
        super(network);
        this.counter = 0;
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
        if (!(local instanceof Market) || super.addLocal(local) == false) {
            return false;
        }
        this.counter++;
        return true;
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
        if (super.removeLocal(name) == false) {
            return false;
        }
        this.counter--;
        return true;
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
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Market)) {
            return false;
        }
        Market market = (Market) local;
        market.addClient(neededStock);
        return true;
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
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Market)) {
            return -1;
        }
        Market market = (Market) local;
        return market.serveClient(stock);
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
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Market)) {
            return -1;
        }
        Market market = (Market) local;
        return market.HowMuchTheClientNeeds();
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
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Market)) {
            return -1;
        }
        Market market = (Market) local;
        return market.getNumberOfClients();
    }

    /**
     * Getter for the number of Locals.
     *
     * @return the number of Locals
     */
    @Override
    public int getNumberOfLocals() {
        return this.counter;
    }

    /**
     * Getter for the Markets.
     *
     * @return the Markets iterator
     */
    @Override
    public Iterator<IMarket> getMarkets() {
        Iterator<ILocal> localsIterator = super.network.vertices();
        UnorderedListADT<IMarket> marketsList = new LinkedUnorderedList<>();
        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();
            if (currentLocal instanceof Market) {
                marketsList.addToRear((IMarket) currentLocal);
            }
        }
        return marketsList.iterator();
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printLocals() {
        System.out.println("*********************");
        System.out.println("-------MARKETS-------");
        System.out.println("*********************");
        System.out.println("Number of markets: " + this.getNumberOfLocals());
        Iterator<IMarket> marketsIterator = this.getMarkets();
        while (marketsIterator.hasNext()) {
            IMarket currentMarket = marketsIterator.next();
            currentMarket.printObject();
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON array
     */
    @Override
    public JSONArray localsToJson() {
        JSONArray jMarkets = new JSONArray();
        Iterator<IMarket> marketsIterator = this.getMarkets();
        while (marketsIterator.hasNext()) {
            ILocal currentMarket = marketsIterator.next();
            jMarkets.add(currentMarket.localToJson());
        }
        return jMarkets;
    }
}
