package Locals;

import org.json.simple.JSONObject;

/**
 *
 * @author Rui Neto
 */
public interface ILocal {

    /**
     * Getter for the name of the Local.
     *
     * @return the name of the Local
     */
    String getName();

    /**
     * Prints the object to the console.
     */
    void printObject();

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    JSONObject localToJson();
}
