package bakery;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents a list of Items
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-12
 */
class ItemList extends IDList<BakeryItem>
{
    /**
     * Constructor for ItemList
     */
    ItemList()
    {
        super();
    }

    /**
     * Returns an ArrayList of String representations of each item in this item
     * list
     *
     * @return an ArrayList of String representations of each item in this item
     * list
     */
    ArrayList<String> itemListString()
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        ArrayList<String> result = new ArrayList<String>();
        for (Map.Entry<Integer, BakeryItem> entry : this.getContents()
            .entrySet())
        {
            result.add(entry.getKey() + "\t" + entry.getValue().getName() +
                           " " + entry.getValue().getCategory() + " " +
                           money.format(entry.getValue().getPrice()));
        }
        return result;
    }

    /**
     * Print all Items in this ItemList to the console
     */
    void printAllItems()
    {
        DecimalFormat money = new DecimalFormat("$0.00");
        System.out
            .printf("%-5s %-40s %-30s %10s %n", "ID", "Item Name", "Category",
                    "Price");
        System.out.println(
            "-------------------------------------------------------------"
                + "------------------------------------------------------");
        for (Map.Entry<Integer, BakeryItem> entry : this.getContents()
            .entrySet())
        {
            System.out.printf("%-5s %-40s %-30s %10s %n", entry.getKey(),
                              entry.getValue().getName(),
                              entry.getValue().getCategory(),
                              money.format(entry.getValue().getPrice()));
        }
        System.out.println(
            "-------------------------------------------------------------"
                + "------------------------------------------------------\n\n");

    }

    /**
     * Write the contents of this item list to the PrintWriter output
     *
     * @param output
     *     the PrintWriter
     */
    void writeAllItems(PrintWriter output)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        for (Map.Entry<Integer, BakeryItem> entry : this.getContents()
            .entrySet())
        {
            output.println(entry.getKey() + "\t" +
                               entry.getValue().getName() + "\t" +
                               entry.getValue().getCategory() + "\t" +
                               df.format(entry.getValue().getPrice()) + "\t");
        }
    }
}
