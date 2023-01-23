package Locals;

/**
 *
 * @author Rui Neto
 */
public interface IMarket extends ILocal {

    /**
     * Adds a client.
     *
     * @param neededStock the stock that the client needs
     * @throws IllegalArgumentException if the neededStock is less or equal than
     * zero
     */
    public void addClient(int neededStock);

    /**
     * Serves the first Client.
     *
     * @param stock the served stock
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no clients
     * @return the stock left to serve
     */
    public int serveClient(int stock);

    /**
     * Returns the stock that the first Client needs.
     *
     * @throws IllegalStateException if there are no clients
     * @return the stock
     */
    public int HowMuchTheClientNeeds();

    /**
     * Getter for the number of clients.
     *
     * @return the number of clients
     */
    public int getNumberOfClients();
}
