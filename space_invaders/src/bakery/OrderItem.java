package bakery;

import java.text.DecimalFormat;

/**
 * This class represents an Item ordered at the Bakery.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-12
 */
class OrderItem
{
    private int id;
    private int quantity;
    private String name;
    private String category;
    private double price;

    /**
     * Constructs a new order item
     *
     * @param id
     *     the id of the item ordered
     * @param quantity
     *     the quantity of the item ordered
     * @param items
     *     the items list containing the item's name, category, and unit price
     */
    OrderItem(int id, int quantity, ItemList items)
    {
        this.id = id;
        this.quantity = quantity;
        this.name = items.get(id).getName();
        this.category = items.get(id).getCategory();
        this.price = items.get(id).getPrice() * this.quantity;
    }

    /**
     * Constructs a new order item
     *
     * @param id
     *     the id of the item ordered
     * @param quantity
     *     the quantity of the item ordered
     * @param name
     *     the name of the item ordered
     * @param category
     *     the category name of the item ordered
     * @param price
     *     the unit price of the item ordered
     */
    OrderItem(int id, int quantity, String name, String category, double price)
    {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price * this.quantity;
    }

    /**
     * Accessor for this OrderItem's id number
     *
     * @return this OrderItem's id number
     */
    int getId()
    {
        return id;
    }

    /**
     * Setter this OrderItem's id number
     *
     * @param id
     *     this id number
     */
    void setId(int id)
    {
        this.id = id;
    }

    /**
     * Accessor for this OrderItem's quantity
     *
     * @return this OrderItem's quantity
     */
    int getQuantity()
    {
        return quantity;
    }

    /**
     * Returns the price for this item by multiplying the price for the item
     * with the this item's id in items and this item's quantity
     *
     * @return the price for this item
     */
    double getPrice()
    {
        return this.price;
    }

    /**
     * Setter this OrderItem's quantity
     *
     * @param quantity
     *     the quantity
     */
    void setQuantity(int quantity)
    {
        double unitPrice = this.price / this.quantity;
        this.quantity = quantity;
        this.price = unitPrice * this.quantity;
    }

    /**
     * Accessor for this OrderItem's name
     *
     * @return this OrderItem's name
     */
    String getName()
    {
        return name;
    }

    /**
     * Setter this OrderItem's name
     *
     * @param name
     *     the name
     */
    void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor for this OrderItem's category
     *
     * @return this OrderItem's category
     */
    String getCategory()
    {
        return category;
    }

    /**
     * Setter this OrderItem's category
     *
     * @param category
     *     the category
     */
    void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * String representation of this OrderItem
     *
     * @return String representation of this OrderItem
     */
    public String toString()
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        return this.quantity + "x " + this.name + " " + this.category + " = "
            + money.format(this.price);
    }
}
