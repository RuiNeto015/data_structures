package Locals;

/**
 *
 * @author Rui Neto
 */
public interface IWarehouse extends ILocal {

    /**
     * Getter for the Warehouse capacity.
     *
     * @return the Warehouse capacity
     */
    public int getCapacity();

    /**
     * Setter for the Warehouse capacity.
     *
     * @param capacity the Warehouse capacity
     * @throws IllegalArgumentException if the capacity is less or equal than
     * zero
     */
    public void setCapacity(int capacity);

    /**
     * Getter for the Warehouse stock.
     *
     * @return the Warehouse stock
     */
    public int getStock();

    /**
     * Loads the Warehouse stock.
     *
     * @param stock the stock to be added
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @return the loaded stock
     */
    public int loadStock(int stock);

    /**
     * Unloads stock from the Warehouse.
     *
     * @param stock the stock to be unloaded
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @return the unloaded stock
     */
    public int unloadStock(int stock);
}
