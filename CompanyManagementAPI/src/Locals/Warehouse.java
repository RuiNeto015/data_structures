package Locals;

import org.json.simple.JSONObject;

/**
 *
 * @author Rui Neto
 */
public class Warehouse extends Local implements IWarehouse {

    private int capacity;
    private int stock;

    /**
     * Warehouse class constructor.
     *
     * @param name the name of the Warehouse
     * @param capacity the capacity of the Warehouse
     * @throws IllegalArgumentException if the capacity is less or equal than
     * zero
     */
    public Warehouse(String name, int capacity) {
        super(name);
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is invalid.");
        }
        this.capacity = capacity;
    }

    /**
     * Getter for the Warehouse capacity.
     *
     * @return the Warehouse capacity
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Setter for the Warehouse capacity.
     *
     * @param capacity the Warehouse capacity
     * @throws IllegalArgumentException if the capacity is less or equal than
     * zero
     */
    @Override
    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is invalid.");
        }
        this.capacity = capacity;
    }

    /**
     * Getter for the Warehouse stock.
     *
     * @return the Warehouse stock
     */
    @Override
    public int getStock() {
        return this.stock;
    }

    /**
     * Loads the Warehouse stock.
     *
     * @param stock the stock to be added
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @return the loaded stock
     */
    @Override
    public int loadStock(int stock) {
        if (stock <= 0) {
            throw new IllegalArgumentException("Stock is invalid.");
        }
        int leftSpace = this.capacity - this.stock;
        if (stock >= leftSpace) {
            this.stock += leftSpace;
            return leftSpace;
        }
        this.stock += stock;
        return stock;
    }

    /**
     * Unloads stock from the Warehouse.
     *
     * @param stock the stock to be unloaded
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @return the unloaded stock
     */
    @Override
    public int unloadStock(int stock) {
        if (stock <= 0) {
            throw new IllegalArgumentException("Stock is invalid.");
        }
        if (this.stock <= stock) {
            int unloaded = this.stock;
            this.stock = 0;
            return unloaded;
        }
        this.stock -= stock;
        return stock;
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printObject() {
        super.printObject();
        System.out.println("Capacity: " + this.capacity);
        System.out.println("Stock: " + this.stock);
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    @Override
    public JSONObject localToJson() {
        JSONObject jWarehouse = super.localToJson();
        jWarehouse.put("capacity", this.capacity);
        jWarehouse.put("stock", this.stock);
        return jWarehouse;
    }
}
