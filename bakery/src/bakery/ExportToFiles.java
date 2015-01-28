package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class represents a file exporter that exports files based on interaction
 * from the user.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class ExportToFiles implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Construct a new file exporter
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    ExportToFiles(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Export files based on interaction from the user (triggered by an
     * ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        String itemsPath = "bakeryItems.txt";
        File itemsFile = new File(itemsPath);
        PrintWriter itemsOutput;
        String ordersPath = "orders.txt";
        File ordersFile = new File(ordersPath);
        PrintWriter ordersOutput;
        JOptionPane
            .showMessageDialog(this.frame, "The export will be written to " +
                itemsPath + " and\n" + ordersPath + " in " +
                "this application's directory.\n" +
                "Existing files with those names " +
                "will be overwritten. \nSelect okay" +
                " to continue.");
        try
        {
            itemsOutput = new PrintWriter(itemsFile);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this.frame, "Cannot write to " +
                itemsPath + ".");
            return;
        }

        try
        {
            ordersOutput = new PrintWriter(ordersFile);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this.frame, "Cannot write to " +
                ordersPath + ".");
            return;
        }

        this.writeItems(itemsOutput);
        itemsOutput.close();

        this.writeOrders(ordersOutput);
        ordersOutput.close();
    }

    /**
     * Writes all items to the PrintWriter output
     *
     * @param output
     *     the PrintWriter output
     */
    private void writeItems(PrintWriter output)
    {
        output.println("BakeryItemID\tBakeryItemName\tCategory\tPrice");
        bakery.getItems().writeAllItems(output);
    }

    /**
     * Writes all orders to the PrintWriter output
     *
     * @param output
     *     the PrintWriter output
     */
    private void writeOrders(PrintWriter output)
    {
        output.println("CustomerID\tLastName\tAddress\tCity\tState\tZipCode" +
                           "\tOrderID\tPaid?\tOrderDate\tPickupDate\t" +
                           "BakeryItemID\tBakeryItemName\t" +
                           "BakeryItemCategory\tQuantity\tPrice\tTotal\t" +
                           "DiscountUsedOnOrder\tTotalDue\tAvailableDiscount" +
                           "\tCurrentLoyalty");
        bakery.getClients().writeAllOrders(output);
    }
}
