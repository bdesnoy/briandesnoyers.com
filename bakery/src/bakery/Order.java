package bakery;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class represents an Order made at a Bakery
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-11
 */
class Order
{
    /**
     * id number of order
     */
    private int id;
    /**
     * date order was made
     */
    private BakeryDate ordered;
    /**
     * date of order pick up
     */
    private BakeryDate pickup;
    /**
     * items ordered
     */
    private ArrayList<OrderItem> items;
    /**
     * whether or not the order is paid for
     */
    private boolean paid;
    /**
     * discount used on order
     */
    private double discountUsed;

    /**
     * Constructor for an Order
     *
     * @param id
     *     id number
     * @param orderDate
     *     date Order was made
     * @param pickupDate
     *     pick up date of Order
     */
    Order(int id, BakeryDate orderDate, BakeryDate pickupDate)
    {
        this.id = id;
        this.ordered = orderDate;
        this.pickup = pickupDate;
        this.items = new ArrayList<OrderItem>();
        this.paid = false;
        this.discountUsed = 0;
    }

    /**
     * Accessor for this Order's id number
     *
     * @return this Order's id number
     */
    int getId()
    {
        return id;
    }

    /**
     * Setter this Order's id number
     *
     * @param id
     *     this id number
     */
    void setId(int id)
    {
        this.id = id;
    }

    /**
     * Accessor for this Order's order date
     *
     * @return this Order's order date
     */
    BakeryDate getOrdered()
    {
        return ordered;
    }

    /**
     * Setter this Order's order date
     *
     * @param ordered
     *     the order date
     */
    void setOrdered(BakeryDate ordered)
    {
        this.ordered = ordered;
    }

    /**
     * Accessor for this Order's pick up date
     *
     * @return this Order's pick up date
     */
    BakeryDate getPickup()
    {
        return pickup;
    }

    /**
     * Setter this Order's pick up date
     *
     * @param pickup
     *     the pick up date
     */
    void setPickup(BakeryDate pickup)
    {
        this.pickup = pickup;
    }

    /**
     * Accessor for this Order's items
     *
     * @return this Order's items
     */
    ArrayList<OrderItem> getItems()
    {
        return items;
    }

    /**
     * Setter this Order's items
     *
     * @param items
     *     the items
     */
    void setItems(ArrayList<OrderItem> items)
    {
        this.items = items;
    }

    /**
     * Accessor for whether or not this Order is paid for
     *
     * @return whether or not this Order is paid for
     */
    boolean isPaid()
    {
        return paid;
    }

    /**
     * Setter whether or not this Order is paid for
     *
     * @param paid
     *     the boolean
     */
    void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    /**
     * Accessor for this Order's discount used
     *
     * @return this Order's discount used
     */
    double getDiscountUsed()
    {
        return this.discountUsed;
    }

    /**
     * Setter this Order's discount used
     *
     * @param discountUsed
     *     the discount used
     */
    void setDiscountUsed(double discountUsed)
    {
        this.discountUsed = discountUsed;
    }

    /**
     * Returns the total for this order based on prices in items
     *
     * @return the total for this order
     */
    double getSubTotal()
    {
        double total = 0;
        for (OrderItem item : this.items)
        {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Returns the total for this order based on prices in items
     *
     * @return the total for this order
     */
    double getTotal()
    {
        return this.getSubTotal() - this.discountUsed;
    }

    /**
     * Adds the item with id id to this order
     *
     * @param ident
     *     the id of the item to add
     * @param quantity
     *     the quantity of the item purchased
     * @param itms
     *     the HashMap of the items the bakery has which contains the item's
     *     name, category, and price
     */
    void addItem(int ident, int quantity, ItemList itms)
    {
        this.items.add(new OrderItem(ident, quantity, itms));
    }

    /**
     * Adds the item with the given id id to this order
     *
     * @param ident
     *     the id of the item to add
     * @param quantity
     *     the quantity of the item purchased
     * @param name
     *     the name of the item to add to this order
     * @param category
     *     the category of the item to add to this order
     * @param price
     *     the unit price of the item to add to this order
     */
    void addItem(int ident, int quantity, String name, String category,
        double price)
    {
        this.items.add(new OrderItem(ident, quantity, name, category, price));
    }

    /**
     * Prints a recipt to the console for the Customer cust
     *
     * @param cust
     *     the customer
     */
    void printRecipt(Customer cust)
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        DecimalFormat orderId = new DecimalFormat("0000000000");
        System.out.println("***********************************************");
        System.out.println("                Order Recipt");
        System.out.printf("%47s %n", "Order ID: " + orderId.format(this.id));
        System.out.println("Order Date: " + this.getOrdered());
        System.out.println("Pickup Date: " + this.getPickup());
        System.out.println("Customer:");
        System.out.println(cust.getName());
        System.out.println(cust.getAddress());
        System.out.println(cust.getCity() + ", " + cust.getState() + " " +
                               cust.getZip());
        System.out.println();
        System.out.printf("%-3s %-15s %-15s %10s %n", "Q", "Item", "Category",
                          "Price");
        System.out.println("-----------------------------------------------");
        for (OrderItem item : this.items)
        {
            System.out.printf("%-3s %-15s %-15s %10s %n", item.getQuantity(),
                              cutTo(item.getName(), 14),
                              cutTo(item.getCategory(), 14),
                              money.format(item.getPrice()));
        }
        System.out.println();
        System.out.println("Subtotal: " + money.format(this.getSubTotal()));
        System.out.println("Discount: " + money.format(this.getDiscountUsed()));
        System.out.println("Total:  " + money.format(this.getTotal()));
        if (!this.paid)
        {
            System.out.printf("%47s %n", "PAYMENT ON THIS ORDER IS PENDING");
        }
        System.out.println("***********************************************");
    }

    /**
     * Whether or not this Order contains an Item with the given id
     *
     * @param itemId
     *     the given id
     * @return Whether or not this Order contains an Item with the given id
     */
    boolean contains(int itemId)
    {
        for (OrderItem item : this.items)
        {
            if (item.getId() == itemId)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representation of this order
     *
     * @return a String representation of this order
     */
    public String toString()
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        String isPaid;
        if (this.isPaid())
        {
            isPaid = "Paid";
        }
        else
        {
            isPaid = "Unpaid";
        }

        return this.id + " " + this.getOrdered() + " " + money
            .format(this.getTotal()) + " " + isPaid;
    }

    /**
     * Cuts String s to length n
     *
     * @param s
     *     the String to cut
     * @param n
     *     the length to cut the String s to
     * @return the cut String
     */
    static String cutTo(String s, int n)
    {
        return s.substring(0, Math.min(n, s.length()));
    }
}
