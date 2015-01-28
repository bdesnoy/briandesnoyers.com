package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a report printer that prints reports to the console
 * based on input from the user.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class PrintedReports implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Constructs a new report printer
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    PrintedReports(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prints reports to the console based on input from the user (triggered by
     * an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Set up the options */
        String[] reports = { "Orders By Customer", "Orders By Order Date",
            "Orders By Pickup Date", "Orders By Item", "Unpaid Orders",
            "Items for Sale" };

        /** Handle input of month **/
        String reportType = (String) JOptionPane
            .showInputDialog(frame, "Select the report type",
                             "Select the report type", messageType, null,
                             reports, "Unpaid Orders");
        if (reportType == null)
        {
            return;
        }

        if (reportType.equals(reports[0])) // by customer
        {
            System.out
                .println("\n***********************************************");
            System.out.println("Printing Recipts for All Orders by Customer");
            System.out
                .println("***********************************************\n");
            /** Select the customer */
            SelectCustomer selectCust = new SelectCustomer(this.frame,
                                                           this.bakery);
            selectCust.actionPerformed(actionEvent);
            int custId = selectCust.getId();
            if (custId == -1)
            {
                return;
            }
            bakery.getClients().printReceiptsByCustomer(custId);
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");
        }
        else if (reportType.equals(reports[1])) // by order date
        {
            System.out
                .println("\n***********************************************");
            System.out.println("Printing Recipts for All Orders by Order Date");
            System.out
                .println("***********************************************\n");
            /** Select the order date */
            SelectDate selectOrder = new SelectDate(this.frame, "order");
            selectOrder.actionPerformed(actionEvent);
            BakeryDate orderDate = selectOrder.getDate();
            if (orderDate == null)
            {
                return;
            }
            bakery.getClients().printReceiptsByOrderDate(orderDate);
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");
        }
        else if (reportType.equals(reports[2])) // by pickup date
        {
            System.out
                .println("\n***********************************************");
            System.out
                .println("Printing Recipts for All Orders By Pickup " + "Date");
            System.out
                .println("***********************************************\n");
            /** Select the pickup date */
            SelectDate selectPickup = new SelectDate(this.frame, "order");
            selectPickup.actionPerformed(actionEvent);
            BakeryDate pickupDate = selectPickup.getDate();
            if (pickupDate == null)
            {
                return;
            }
            bakery.getClients().printReceiptsByPickupDate(pickupDate);
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");
        }
        else if (reportType.equals(reports[3])) // orders by item
        {
            System.out
                .println("\n***********************************************");
            System.out.println("Printing Recipts for All Orders By Item");
            System.out
                .println("***********************************************\n");
            /** Select an item */
            SelectItem selectItem = new SelectItem(this.frame, this.bakery);
            selectItem.actionPerformed(actionEvent);
            int itemId = selectItem.getId();
            if (itemId == -1)
            {
                return;
            }
            bakery.getClients().printReceiptsByItem(itemId);
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");
        }
        else if (reportType.equals(reports[4])) // unpaid orders
        {
            System.out
                .println("\n***********************************************");
            System.out.println("Printing Recipts for All Unpaid Orders");
            System.out
                .println("***********************************************\n");
            bakery.getClients().printUnpaidReceipts();
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");
        }
        else if (reportType.equals(reports[5])) // items for sale
        {
            System.out
                .println("\n***********************************************");
            System.out.println("Printing Listing of All Items for Sale");
            System.out
                .println("***********************************************\n");
            this.bakery.getItems().printAllItems();
            System.out.println("\n\nEnd of Report");
            System.out
                .println("***********************************************\n");

        }
    }
}
