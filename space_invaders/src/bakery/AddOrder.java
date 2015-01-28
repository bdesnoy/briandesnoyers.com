package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents an Order adder that adds an Order based on input from
 * the user. The Order can be accessed via the getOrder method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class AddOrder implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Construct a new Order adder
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    AddOrder(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to add an Order (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Select the customer placing the order */
        SelectCustomer selectCust = new SelectCustomer(this.frame, this.bakery);
        selectCust.actionPerformed(actionEvent);
        int custId = selectCust.getId();
        if (custId == -1)
        {
            return;
        }
        Customer cust = bakery.getClients().get(custId);

        /** Select the order date */
        BakeryDate orderDate = new BakeryDate();

        /** Select the pickup date */
        SelectDate selectPickup = new SelectDate(this.frame, "pickup");
        selectPickup.actionPerformed(actionEvent);
        BakeryDate pickupDate = selectPickup.getDate();
        if (pickupDate == null)
        {
            return;
        }

        /** Create the order */
        Order order = new Order(bakery.getClients().getNextOrderId(), orderDate,
                                pickupDate);
        bakery.getClients().get(custId).getOrders().add(order.getId(), order);

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
        boolean paid = JOptionPane
            .showConfirmDialog(frame, "Is the client paying for this item now?",
                               "Continue?", JOptionPane.YES_NO_OPTION) < 1;
        if (paid)
        {
            order.setPaid(true);
        }
        cust.addToLoyalty(order);
        order.printRecipt(bakery.getClients().get(custId));
    }
}
