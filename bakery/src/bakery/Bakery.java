package bakery;

import java.util.ArrayList;

/**
 * This class represents the state of the bakery.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-10
 */
class Bakery
{
    /**
     * The frequency a discount should be given
     */
    private static double discountFrequency = 100;
    /**
     * The amount of a new discount
     */
    private static double discountAmt = 10;
    /**
     * The states available to pick
     */
    private String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC",
        "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
        "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE",
        "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI",
        "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV",
        "WY" };
    /**
     * The default state- the location of the bakery
     */
    private String defaultState = "NC";
    /**
     * The item categories within the bakery
     */
    private ArrayList<String> itemCategories = new ArrayList<String>();
    /**
     * The clients of the bakery and their orders
     */
    private CustomerList clients;
    /**
     * The items of the bakery
     */
    private ItemList items;

    /**
     * Constructs a new bakery state
     */
    Bakery()
    {
        this.clients = new CustomerList();
        this.items = new ItemList();
    }

    /**
     * Accessor for the items the bakery sells
     *
     * @return the items the bakery sells
     */
    public ItemList getItems()
    {
        return this.items;
    }

    /**
     * Accessor for the bakery's clients and their orders
     *
     * @return Accessor for the bakery's clients and their orders
     */
    public CustomerList getClients()
    {
        return this.clients;
    }

    /**
     * Accessor for the discount frequency of the bakery
     *
     * @return the discount frequency of the bakery
     */
    static double getDiscountFrequency()
    {
        return discountFrequency;
    }

    /**
     * Accessor for the bakery's discount amount
     *
     * @return the bakery's discount amount
     */
    static double getDiscountAmt()
    {
        return discountAmt;
    }

    /**
     * Accessor for the list of possible states
     *
     * @return the list of possible states
     */
    String[] getStates()
    {
        return states;
    }

    /**
     * Accessor for the default state- the state the bakery is in
     *
     * @return the default state- the state the bakery is in
     */
    String getDefaultState()
    {
        return defaultState;
    }

    /**
     * Accessor for the item categories of this bakery
     *
     * @return the item categories of this bakery
     */
    ArrayList<String> getItemCategories()
    {
        return this.itemCategories;
    }

    /**
     * Modifier for the items list of this bakery
     *
     * @param items
     *     an updated items list for this bakery
     */
    void setItems(ItemList items)
    {
        this.items = items;
    }
}
