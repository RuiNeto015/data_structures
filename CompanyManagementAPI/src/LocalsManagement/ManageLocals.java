package LocalsManagement;

import AdaptedDataStructures.IAdaptedNetwork;
import Locals.ILocal;
import java.util.Iterator;
import org.json.simple.JSONArray;

/**
 *
 * @author Simão
 */
public abstract class ManageLocals implements IManageLocals {

    protected IAdaptedNetwork<ILocal> network;

    /**
     * ManageLocals class constructor.
     *
     * @param network the network
     * @throws IllegalArgumentException if the network is null or not empty
     */
    public ManageLocals(IAdaptedNetwork<ILocal> network) {
        if (network == null || (!network.isEmpty())) {
            throw new IllegalArgumentException("The network is invalid");
        }
        this.network = network;
    }

    protected ILocal getLocal(String name) {
        Iterator<ILocal> localsIterator = this.network.vertices();

        while (localsIterator.hasNext()) {
            ILocal currentLocal = localsIterator.next();
            if (currentLocal.getName().equals(name)) {
                return currentLocal;
            }
        }
        return null;
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
        if (local == null) {
            throw new IllegalArgumentException("Local is null.");
        }
        if (this.getLocal(local.getName()) != null) {
            return false;
        }
        this.network.addVertex(local);
        return true;
    }

    protected void checkIfArgumentAndStateAreValids(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is invalid.");
        }
        if (this.network.isEmpty()) {
            throw new IllegalStateException("There are no Locals.");
        }
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
        this.checkIfArgumentAndStateAreValids(name);
        ILocal local = this.getLocal(name);

        if (local == null) {
            return false;
        }
        this.network.removeVertex(local);
        return true;
    }

    /**
     * Getter for the number of Locals.
     *
     * @return the number of Locals
     */
    @Override
    public abstract int getNumberOfLocals();

    /**
     * Prints the object to the console.
     */
    @Override
    public abstract void printLocals();

    /**
     * Converts the object to JSON.
     *
     * @return the JSON array
     */
    public abstract JSONArray localsToJson();
}
