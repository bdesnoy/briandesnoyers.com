package bakery;

/**
 * This class represents an Item for sale at the Bakery.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-11
 */
class BakeryItem
{
    /**
     * category of item
     */
    private String category;
    /**
     * name of item
     */
    private String name;
    /**
     * price of item
     */
    private double price;

    /**
     * Constructor for BakeryItem
     *
     * @param category
     *     category of item
     * @param name
     *     name of item
     * @param price
     *     price of item
     */
    BakeryItem(String category, String name, double price)
    {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    /**
     * Accessor for this BakeryItem's category
     *
     * @return this BakeryItem's category
     */
    String getCategory()
    {
        return category;
    }

    /**
     * Setter this BakeryItem's category
     *
     * @param category
     *     the category
     */
    void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Accessor for this BakeryItem's name
     *
     * @return this BakeryItem's name
     */
    String getName()
    {
        return name;
    }

    /**
     * Setter this BakeryItem's name
     *
     * @param name
     *     the name
     */
    void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor for this BakeryItem's price
     *
     * @return this BakeryItem's price
     */
    double getPrice()
    {
        return price;
    }

    /**
     * Setter this BakeryItem's price
     *
     * @param price
     *     the price
     */
    void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * String representation of this BakeryItem
     *
     * @return String representation of this BakeryItem
     */
    public String toString()
    {
        return this.name + " " + this.category;
    }

}
