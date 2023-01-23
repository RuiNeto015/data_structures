package Locals;

import DataStructures.LinkedQueue;
import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.QueueADT;
import DataStructuresInterfaces.UnorderedListADT;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rui Neto
 */
public class Market extends Local implements IMarket {

    private QueueADT<Integer> queue;

    /**
     * Market class constructor.
     *
     * @param name the name of the market
     * @throws IllegalArgumentException if the name is null or blank
     */
    public Market(String name) {
        super(name);
        this.queue = new LinkedQueue<>();
    }

    /**
     * Adds a client.
     *
     * @param neededStock the stock that the client needs
     * @throws IllegalArgumentException if the neededStock is less or equal than
     * zero
     */
    @Override
    public void addClient(int neededStock) {
        if (neededStock <= 0) {
            throw new IllegalArgumentException("Invalid Needed Stock.");
        }
        this.queue.enqueue(neededStock);
    }

    /**
     * Serves the first Client.
     *
     * @param stock the served stock
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no clients
     * @return the stock left to serve
     */
    @Override
    public int serveClient(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Invalid Stock.");
        }
        if (this.queue.isEmpty()) {
            throw new IllegalStateException("There are no Clients to serve.");
        }
        if (stock >= this.queue.first()) {
            this.queue.dequeue();
            return 0;
        }
        int leftToServe = this.queue.first() - stock;
        UnorderedListADT<Integer> unorderedList = new LinkedUnorderedList<>();
        this.queue.dequeue();
        unorderedList.addToRear(leftToServe);

        while (!this.queue.isEmpty()) {
            unorderedList.addToRear(this.queue.dequeue());
        }
        while (!unorderedList.isEmpty()) {
            this.queue.enqueue(unorderedList.removeFirst());
        }
        return leftToServe;
    }

    /**
     * Returns the stock that the first Client needs.
     *
     * @throws IllegalStateException if there are no clients
     * @return the stock
     */
    @Override
    public int HowMuchTheClientNeeds() {
        if (this.queue.isEmpty()) {
            throw new IllegalStateException("There are no Clients to serve.");
        }
        return this.queue.first();
    }

    /**
     * Getter for the number of clients.
     *
     * @return the number of clients
     */
    @Override
    public int getNumberOfClients() {
        return this.queue.size();
    }

    private Iterator<Integer> getClients() {
        UnorderedListADT<Integer> unorderedList = new LinkedUnorderedList<>();
        QueueADT<Integer> temp = new LinkedQueue<>();

        while (!this.queue.isEmpty()) {
            int value = this.queue.dequeue();
            unorderedList.addToRear(value);
            temp.enqueue(value);
        }
        this.queue = temp;
        return unorderedList.iterator();
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printObject() {
        super.printObject();
        System.out.println("Clients:");

        Iterator<Integer> clientsIterator = this.getClients();
        int counter = 0;
        while (clientsIterator.hasNext()) {
            int stock = clientsIterator.next();
            System.out.println(counter++ + ": " + stock);
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    @Override
    public JSONObject localToJson() {
        JSONObject jMarket = super.localToJson();
        JSONArray jClients = new JSONArray();

        Iterator<Integer> clientsIterator = this.getClients();
        while (clientsIterator.hasNext()) {
            int stock = clientsIterator.next();
            jClients.add(stock);
        }
        jMarket.put("clients", jClients);
        return jMarket;
    }
}
