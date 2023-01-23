package LocalsManagement;

import AdaptedDataStructures.IAdaptedNetwork;
import DataStructures.LinkedUnorderedList;
import DataStructuresInterfaces.UnorderedListADT;
import Locals.ILocal;
import Locals.IWarehouse;
import Locals.Warehouse;
import java.util.Iterator;
import org.json.simple.JSONArray;

/**
 *
 * @author Simão
 */
public class ManageWarehouses extends ManageLocals implements IManageWarehouses {

    private int counter;

    /**
     * ManageWarehouses class constructor.
     *
     * @param network the network
     * @throws IllegalArgumentException if the network is null or not empty
     */
    public ManageWarehouses(IAdaptedNetwork<ILocal> network) {
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
        if (!(local instanceof Warehouse) || super.addLocal(local) == false) {
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
     * Getter for a Warehouse's capacity.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse capacity, -1 if the warehouse doesn't exist
     */
    @Override
    public int getWarehouseCapacity(String name) {
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Warehouse)) {
            return -1;
        }
        Warehouse warehouse = (Warehouse) local;
        return warehouse.getCapacity();
    }

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
    @Override
    public boolean setWarehouseCapacity(String name, int capacity) {
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Warehouse)) {
            return false;
        }
        Warehouse warehouse = (Warehouse) local;
        warehouse.setCapacity(capacity);
        return true;
    }

    /**
     * Getter for a Warehouse's stock.
     *
     * @param name the name of the Warehouse
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no warehouses
     * @return the Warehouse stock, -1 if the warehouse doesn't exist
     */
    @Override
    public int getWarehouseStock(String name) {
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Warehouse)) {
            return -1;
        }
        Warehouse warehouse = (Warehouse) local;
        return warehouse.getStock();
    }

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
    @Override
    public int loadWarehouseStock(String name, int stock) {
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Warehouse)) {
            return -1;
        }
        Warehouse warehouse = (Warehouse) local;
        return warehouse.loadStock(stock);
    }

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
    @Override
    public int unloadWarehouseStock(String name, int stock) {
        super.checkIfArgumentAndStateAreValids(name);
        ILocal local = super.getLocal(name);
        if (local == null || !(local instanceof Warehouse)) {
            return -1;
        }
        Warehouse warehouse = (Warehouse) local;
        return warehouse.unloadStock(stock);
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
     * Getter for the Warehouses.
     *
     * @return the Warehouses iterator
     */
    @Override
    public Iterator<IWarehouse> getWarehouses() {
        Iterator<ILocal> localsIterator = super.network.vertices();
        UnorderedListADT<IWarehouse> warehousesList = new LinkedUnorderedList<>();
        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();
            if (currentLocal instanceof Warehouse) {
                warehousesList.addToRear((IWarehouse) currentLocal);
            }
        }
        return warehousesList.iterator();
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printLocals() {
        System.out.println("*********************");
        System.out.println("------WAREHOUSES-----");
        System.out.println("*********************");
        System.out.println("Number of warehouses: " + this.getNumberOfLocals());
        Iterator<IWarehouse> warehousesIterator = this.getWarehouses();
        while (warehousesIterator.hasNext()) {
            IWarehouse currentWarehouse = warehousesIterator.next();
            currentWarehouse.printObject();
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON array
     */
    @Override
    public JSONArray localsToJson() {
        JSONArray jWarehouses = new JSONArray();
        Iterator<IWarehouse> warehousesIterator = this.getWarehouses();
        while (warehousesIterator.hasNext()) {
            ILocal currentWarehouse = warehousesIterator.next();
            jWarehouses.add(currentWarehouse.localToJson());
        }
        return jWarehouses;
    }
}
