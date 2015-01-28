package bakery;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * This class represents a Customer.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-10
 */
class Customer
{
    /**
     * name of customer
     */
    private String name;
    /**
     * address of customer
     */
    private String address;
    /**
     * city of customer
     */
    private String city;
    /**
     * state of customer
     */
    private String state;
    /**
     * zip code of customer
     */
    private int zip;
    /**
     * orders of customer
     */
    private OrderList orders;
    /**
     * amount of loyalty customer currently has
     */
    private double loyalty;
    /**
     * the amount the customer has earned to have deducted from bill
     */
    private double discount;

    /**
     * Constructor for a Customer
     *
     * @param name
     *     name of the Customer
     * @param address
     *     address of the Customer
     * @param city
     *     city of the Customer
     * @param state
     *     state of the Customer
     * @param zip
     *     zip code of the Customer
     */
    Customer(String name, String address, String city, String state, int zip)
    {
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZip(zip);
        this.setOrders(new OrderList());
        this.setLoyalty(0);
        this.setDiscount(0);
    }

    /**
     * Accessor for this customer's name
     *
     * @return name of this Customer
     */
    String getName()
    {
        return name;
    }

    /**
     * Setter this Customer's name
     *
     * @param name
     *     the name
     */
    void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor for this customer's address
     *
     * @return address of this Customer
     */
    String getAddress()
    {
        return address;
    }

    /**
     * Setter this Customer's address
     *
     * @param address
     *     the address
     */
    void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Accessor for this customer's city
     *
     * @return city of this Customer
     */
    String getCity()
    {
        return city;
    }

    /**
     * Setter this Customer's city
     *
     * @param city
     *     the city
     */
    void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Accessor for this customer's state
     *
     * @return state of this Customer
     */
    String getState()
    {
        return state;
    }

    /**
     * Setter this Customer's state
     *
     * @param state
     *     the state
     */
    void setState(String state)
    {
        this.state = state;
    }

    /**
     * Accessor for this Customer's zip code
     *
     * @return zip code of this Customer
     */
    int getZip()
    {
        return zip;
    }

    /**
     * Setter this Costumer's zip code
     *
     * @param zip
     *     the zip code
     */
    void setZip(int zip)
    {
        this.zip = zip;
    }

    /**
     * Accessor for this Customer's orders
     *
     * @return orders of this Customer
     */
    OrderList getOrders()
    {
        return orders;
    }

    /**
     * Setter this Customer's orders
     *
     * @param orders
     *     the orders
     */
    void setOrders(OrderList orders)
    {
        this.orders = orders;
    }

    /**
     * Accessor for this Customer's loyalty
     *
     * @return loyalty of this Customer
     */
    double getLoyalty()
    {
        return loyalty;
    }

    /**
     * Setter this Customer's loyalty
     *
     * @param loyalty
     *     the loyalty
     */
    void setLoyalty(double loyalty)
    {
        this.loyalty = loyalty;
    }

    /**
     * Accessor for this Customer's discount
     *
     * @return discount of this Customer
     */
    double getDiscount()
    {
        return discount;
    }

    /**
     * Setter this Customer's discount
     *
     * @param discount
     *     the discount
     */
    void setDiscount(double discount)
    {
        this.discount = discount;
    }

    /**
     * Adds an amount to the loyalty program and updates the discount
     * accordingly
     *
     * @param amt
     *     the amount being added to the loyalty program
     */
    void addToLoyalty(double amt)
    {
        this.loyalty += amt;
        while (this.loyalty >= Bakery.getDiscountFrequency())
        {
            this.loyalty -= Bakery.getDiscountFrequency();
            this.discount += Bakery.getDiscountAmt();
        }
    }

    /**
     * Print the receipts for all orders by this Customer
     */
    void printReceipts()
    {
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            entry.getValue().printRecipt(this);
        }
    }

    /**
     * Print the receipts for all orders by this Customer made on the given
     * date
     *
     * @param date
     *     the given date
     */
    void printReceiptsByOrderDate(BakeryDate date)
    {
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            if (entry.getValue().getOrdered().equals(date))
            {
                entry.getValue().printRecipt(this);
            }
        }
    }

    /**
     * Print the receipts for all orders by this Customer picked up on the given
     * date
     *
     * @param date
     *     the given date
     */
    void printReceiptsByPickupDate(BakeryDate date)
    {
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            if (entry.getValue().getPickup().equals(date))
            {
                entry.getValue().printRecipt(this);
            }
        }
    }

    /**
     * Prints receipts of all orders of this Customer that contain the item with
     * the given item id number
     *
     * @param itemId
     *     the given item id number
     */
    void printReceiptsByItem(int itemId)
    {
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            if (entry.getValue().contains(itemId))
            {
                entry.getValue().printRecipt(this);
            }
        }
    }

    /**
     * Prints all unpaid receipts of this Customer
     */
    void printUnpaidReceipts()
    {
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            if (!entry.getValue().isPaid())
            {
                entry.getValue().printRecipt(this);
            }
        }
    }

    /**
     * Adds to loyalty based on the given Oder
     *
     * @param order
     *     the order
     */
    void addToLoyalty(Order order)
    {
        if (order.isPaid())
        {
            if (this.getDiscount() > 0)
            {
                if (this.getDiscount() > order.getTotal())
                {
                    order.setDiscountUsed(order.getTotal());
                    this.setDiscount(this.getDiscount() - order.getTotal());
                }
                else
                {
                    order.setDiscountUsed(this.getDiscount());
                    this.setDiscount(0);
                }
            }
            this.addToLoyalty(order.getTotal());
        }
    }

    /**
     * Revokes loyalty based on given Order
     *
     * @param order
     *     the Order
     */
    void removeFromLoyalty(Order order)
    {
        if (order.isPaid())
        {
            this.loyalty -= order.getSubTotal();
            while (this.loyalty < 0)
            {
                this.loyalty += Bakery.getDiscountFrequency();
                this.discount -= Bakery.getDiscountAmt();
            }
            if (order.getDiscountUsed() > 0)
            {
                this.discount -= order.getDiscountUsed();
                order.setDiscountUsed(0);
            }
        }
    }

    /**
     * Writes all orders made by the Customer with given id to PrintWriter
     * output
     *
     * @param output
     *     the PrintWriter
     * @param custId
     *     the Customer id
     */
    void writeAllOrders(PrintWriter output, int custId)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat zipF = new DecimalFormat("00000");
        String tab = "\t";
        for (Map.Entry<Integer, Order> entry : this.orders.getContents()
            .entrySet())
        {
            String paid;
            if (entry.getValue().isPaid())
            {
                paid = "Yes";
            }
            else
            {
                paid = "No";
            }
            for (OrderItem item : entry.getValue().getItems())
            {
                double discUsed = 0;
                if (entry.getValue().getDiscountUsed() != 0)
                {
                    discUsed = -1 * entry.getValue().getDiscountUsed();
                }
                output.println(custId + tab + this.getName() + tab +
                                   this.getAddress() + tab + this.getCity()
                                   + tab +
                                   this.getState() + tab + zipF
                    .format(this.getZip()) + tab +
                                   entry.getKey() + tab + paid + tab +
                                   entry.getValue().getOrdered() + tab +
                                   entry.getValue().getPickup() + tab +
                                   item.getId() + tab + item.getName() + tab +
                                   item.getCategory() + tab + item.getQuantity()
                                   +
                                   tab + df
                    .format(item.getPrice() / item.getQuantity()) +
                                   tab +
                                   df.format(entry.getValue().getSubTotal()) +
                                   tab + df.format(discUsed) +
                                   tab +
                                   df.format(entry.getValue().getTotal()) + tab
                                   +
                                   df.format(this.getDiscount()) + tab +
                                   df.format(this.getLoyalty()));
            }
        }
    }
}
