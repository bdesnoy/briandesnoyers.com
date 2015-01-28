package bakery;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a list of T, with each entry mapped to an Integer
 * value.
 *
 * @param <T>
 *     the list content to be mapped to an Integer
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-12
 */
class IDList<T>
{
    private TreeMap<Integer, T> contents;

    /**
     * Constructor for an IDList
     */
    IDList()
    {
        this.contents = new TreeMap<Integer, T>();
    }

    /**
     * Accessor for the contents of this IDList
     *
     * @return the contents of this IDList
     */
    TreeMap<Integer, T> getContents()
    {
        return this.contents;
    }

    /**
     * The T mapped to the given int
     *
     * @param n
     *     the given int
     * @return the T mapped to the given int
     */
    T get(int n)
    {
        return contents.get(new Integer(n));
    }

    /**
     * Adds value to this id list assigned to the next available id. If the
     * contents are empty, assigns the id 0
     *
     * @param value
     *     the value to assign
     * @return the id the customer was assigned
     */
    int add(T value)
    {
        int newKey = 0;
        if (!this.contents.isEmpty())
        {
            newKey = contents.lastKey() + 1;
        }
        contents.put(newKey, value);
        return newKey;
    }

    /**
     * Adds value to this id list assigned to the id id
     *
     * @param id
     *     the id to assign the customer
     * @param value
     *     the value to assign
     */
    void add(int id, T value)
    {
        contents.put(id, value);
    }

    /**
     * Converts this ID list to an ArrayList of the values within this IDList
     *
     * @return an ArrayList of the values within this IDList
     */
    ArrayList<T> toArray()
    {
        ArrayList<T> result = new ArrayList<T>();
        for (Map.Entry<Integer, T> entry : this.contents.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * Does this IDList contain the given id?
     *
     * @param id
     *     the id
     * @return true if this IDList contains the id id
     */
    boolean contains(int id)
    {
        return this.contents.containsKey(id);
    }

}
