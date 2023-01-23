package LocalsManagement;

import Locals.ILocal;

/**
 *
 * @author Simão
 */
public interface IManageLocals {

    /**
     * Adds a Local.
     *
     * @param local the local to be added
     * @throws IllegalArgumentException if the local is null
     * @return true if the local was added otherwise false
     */
    boolean addLocal(ILocal local);

    /**
     * Removes a Local.
     *
     * @param name the name of the Local to be removed
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if there are no Locals
     * @return true if the Local was removed otherwise false
     */
    boolean removeLocal(String name);

    /**
     * Getter for the number of Locals.
     *
     * @return the number of Locals
     */
    int getNumberOfLocals();

    /**
     * Prints the object to the console.
     */
    void printLocals();
}
