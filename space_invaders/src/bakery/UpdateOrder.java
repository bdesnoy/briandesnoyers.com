package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents an order updater that updates an order based on user
 * input.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class UpdateOrder implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Constructs an UpdateOrder
     *
     * @param frame
     *     the frame
     * @param bakery
     *     the bakery to update items within
     */
    UpdateOrder(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Performs the Order update action (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Options to perform */
        String[] options = { "Edit payment status", "Edit item quantity",
            "Add item" };

        /** Select a customer */
        SelectCustomer selectCust = new SelectCustomer(this.frame, this.bakery);
        selectCust.actionPerformed(actionEvent);
        int custId = selectCust.getId();
        if (custId == -1)
        {
            return;
        }
        Customer client = bakery.getClients().get(custId);

        /** Select an order */
        SelectOrder selectOrder = new SelectOrder(this.frame, client);
        selectOrder.actionPerformed(actionEvent);
        Order order = selectOrder.getOrder();
        if (order == null)
        {
            return;
        }

        /** Handle selection of option to perform */
        String option = (String) JOptionPane
            .showInputDialog(frame, "How would you " +
                                 "like to " +
                                 "update the " +
                                 "order?", "Select the edit", messageType, null,
                             options, options[0]);
        if (option == null)
        {
            return;
        }
        else if (option.equals(options[0]))
        {
            /** Update payment status */
            this.changePayment(order, client);
        }
        else if (option.equals(options[1]))
        {
            /** Update item quantity */
            this.changeQuantities(order, client, actionEvent);
        }
        else if (option.equals(options[2]))
        {
            /** Add items to order */
            this.addItemsToOrder(order, client, actionEvent);
        }

        JOptionPane.showMessageDialog(this.frame, "The order edit was made "
            + "successfully. The updated receipt will print.");
        order.printRecipt(client);

    }

    /**
     * Handles updating whether or not a customer has paid for an Order
     *
     * @param order
     *     the Order
     * @param cust
     *     the Customer
     */
    private void changePayment(Order order, Customer cust)
    {
        cust.removeFromLoyalty(order);

        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        String[] options = { "Paid", "Unpaid" };

        String current;
        if (order.isPaid())
        {
            current = options[0]; // paid
        }
        else
        {
            current = options[1]; // unpaid
        }

        String pmt = (String) JOptionPane
            .showInputDialog(frame, "Select the new payment status",
                             "Select the new payment status", messageType, null,
                             options, current);
        if (pmt == null)
        {
            return;
        }
        else if (pmt.equals(options[0])) // paid
        {
            order.setPaid(true);
        }
        else if (pmt.equals(options[1])) // unpaid
        {
            order.setPaid(false);
        }

        cust.addToLoyalty(order);
    }

    /**
     * Handles updating the quantity of an Order made by a Customer
     *
     * @param order
     *     the Order
     * @param cust
     *     the Customer
     * @param actionEvent
     *     the ActionEvent
     */
    private void changeQuantities(Order order, Customer cust,
        ActionEvent actionEvent)
    {
        cust.removeFromLoyalty(order);
        /** Select the order to edit */
        SelectOrderItem selectItem = new SelectOrderItem(this.frame, order);
        selectItem.actionPerformed(actionEvent);
        OrderItem item = selectItem.getItem();
        if (item == null)
        {
            return;
        }

        /** Select the quantity */
        QuantityPicker quantityPicker = new QuantityPicker(this.frame,
                                                           "quantity",
                                                           item.getQuantity());
        quantityPicker.actionPerformed(actionEvent);
        int quantity = quantityPicker.getQuantity();
        if (quantity == -1)
        {
            return;
        }

        item.setQuantity(quantity);
        cust.addToLoyalty(order);

        /** Handle editing quantity of another item */
        int resp = JOptionPane
            .showConfirmDialog(frame, "Would you like to edit the" +
                                   " quantity of" + " another item?",
                               "Continue?", JOptionPane.YES_NO_OPTION);

        if (resp < 1) // yes
        {
            this.changeQuantities(order, cust, actionEvent);
        }
    }

    /**
     * Handles updating Items ordered in an Order by a Customer
     *
     * @param order
     *     the Order
     * @param cust
     *     the Customer
     * @param actionEvent
     *     the ActionEvent
     */
    private void addItemsToOrder(Order order, Customer cust,
        ActionEvent actionEvent)
    {
        cust.removeFromLoyalty(order);
        int resp = 0;
        while (resp < 1) // yes
        {
            /** Select an item */
            SelectItem selectItem = new SelectItem(this.frame, this.bakery);
            selectItem.actionPerformed(actionEvent);
            int itemId = selectItem.getId();
            if (itemId == -1)
            {
                break;
            }

            /** Select the quantity */
            QuantityPicker quantityPicker = new QuantityPicker(this.frame,
                                                               "quantity");
            quantityPicker.actionPerformed(actionEvent);
            int quantity = quantityPicker.getQuantity();
            if (quantity == -1)
            {
                break;
            }

            order.addItem(itemId, quantity, this.bakery.getItems());
            resp = JOptionPane
                .showConfirmDialog(frame, "Would you like to add another item?",
                                   "Continue?", JOptionPane.YES_NO_OPTION);
        }
        cust.addToLoyalty(order);
    }
}
