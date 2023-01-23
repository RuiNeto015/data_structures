package LocalsManagement;

import Locals.IWarehouse;
import java.util.Iterator;

/**
 *
 * @author Simão
 */
public interface IManageWarehouses extends IManageLocals {

    /**
     * Getter for a Warehouse's capacity.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse capacity, -1 if the warehouse doesn't exist
     */
    int getWarehouseCapacity(String name);

    /**
     * Setter for a Warehouse's capacity.
     *
     * @param name the name of the Warehouse
     * @param capacity the new Warehouse capacity
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the capacity is less or equal than
     * zero
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse capacity
     */
    boolean setWarehouseCapacity(String name, int capacity);

    /**
     * Getter for a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse stock, -1 if the warehouse doesn't exist
     */
    int getWarehouseStock(String name);

    /**
     * Loads a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @param stock the stock to be loaded
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no warehouses
     * @return the loaded stock, -1 if the warehouse doesn't exist
     */
    int loadWarehouseStock(String name, int stock);

    /**
     * Unloads a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @param stock the stock to be unloaded
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalArgumentException if the stock is less or equal than zero
     * @throws IllegalStateException if there are no warehouses
     * @return the unloaded stock, -1 if the warehouse doesn't exist
     */
    int unloadWarehouseStock(String name, int stock);

    /**
     * Getter for the Warehouses.
     *
     * @return the Warehouses iterator
     */
    Iterator<IWarehouse> getWarehouses();
}
