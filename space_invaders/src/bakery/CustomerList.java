package bakery;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents a List of Customers, with each entry mapped to an
 * Integer value.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-12
 */
class CustomerList extends IDList<Customer>
{
    /**
     * Constructor fo CustomerList
     */
    CustomerList()
    {
        super();
    }

    /**
     * Returns an ArrayList of Strings based on the contents of this customer
     * list
     *
     * @return an ArrayList of Strings based on the contents of this customer
     * list
     */
    ArrayList<String> customerListString()
    {
        DecimalFormat zip = new DecimalFormat("00000");
        ArrayList<String> result = new ArrayList<String>();
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            result.add(entry.getKey() + "\t" + entry.getValue().getName() +
                           " " + zip.format(entry.getValue().getZip()));
        }
        return result;
    }

    /**
     * Returns the next order ID based on previous orders
     *
     * @return the next order ID
     */
    int getNextOrderId()
    {
        int id = 1;
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            if (!entry.getValue().getOrders().getContents().isEmpty())
            {
                int max =
                    entry.getValue().getOrders().getContents().lastKey() + 1;
                id = Math.max(id, max);
            }
        }
        return id;
    }

    /**
     * Print the receipts of all Orders of the Customer with the given id
     *
     * @param customerId
     *     the Customer id
     */
    void printReceiptsByCustomer(int customerId)
    {
        this.getContents().get(customerId).printReceipts();
    }

    /**
     * Print all Receipts for Orders ordered on the given date
     *
     * @param date
     *     the given date
     */
    void printReceiptsByOrderDate(BakeryDate date)
    {
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            entry.getValue().printReceiptsByOrderDate(date);
        }
    }

    /**
     * Print all receipts for Orders that are picked up on the given date
     *
     * @param date
     *     the given date
     */
    void printReceiptsByPickupDate(BakeryDate date)
    {
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            entry.getValue().printReceiptsByPickupDate(date);
        }
    }

    /**
     * Print all receipts for Orders that contain the Item with the given id
     *
     * @param itemId
     *     the given id
     */
    void printReceiptsByItem(int itemId)
    {
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            entry.getValue().printReceiptsByItem(itemId);
        }
    }

    /**
     * Print all receipts for unpaid Orders
     */
    void printUnpaidReceipts()
    {
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            entry.getValue().printUnpaidReceipts();
        }
    }

    /**
     * Writes all orders made by the Customer with given id to print writer
     * output
     *
     * @param output
     *     the PrintWriter
     */
    void writeAllOrders(PrintWriter output)
    {
        for (Map.Entry<Integer, Customer> entry : this.getContents().entrySet())
        {
            entry.getValue().writeAllOrders(output, entry.getKey());
        }
    }

}
