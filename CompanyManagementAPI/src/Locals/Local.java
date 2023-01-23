package Locals;

import java.util.Objects;
import org.json.simple.JSONObject;

/**
 *
 * @author Rui Neto
 */
public class Local implements ILocal {

    private final String name;

    /**
     * Local class constructor.
     *
     * @param name the name of the Local
     * @throws IllegalArgumentException if the name is null or blank
     */
    public Local(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is invalid.");
        }
        this.name = name;
    }

    /**
     * Getter for the name of the Local.
     *
     * @return the name of the Local
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Compares to objects, if they have the same name then they are equal.
     *
     * @param obj the object to compare
     * @return true if are equal otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Local)) {
            return false;
        }
        Local local = (Local) obj;
        return this.name.equals(local.getName());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Prints the object to the console.
     */
    @Override
    public void printObject() {
        System.out.println("--------Local--------");
        System.out.println("Name: " + this.name);
    }

    /**
     * Converts the object to JSON.
     *
     * @return the JSON object
     */
    @Override
    public JSONObject localToJson() {
        JSONObject jLocal = new JSONObject();
        jLocal.put("name", this.name);
        return jLocal;
    }
}
