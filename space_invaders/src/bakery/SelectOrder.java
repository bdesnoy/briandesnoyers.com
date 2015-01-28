package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents an order selector that prompts the user to select an
 * order for a particular customer. The selected order can be accessed via the
 * getOrder method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class SelectOrder implements ActionListener
{
    private JFrame frame;
    private Customer cust;
    private Order order;

    /**
     * Constructs a new order selector
     *
     * @param frame
     *     the parent frame
     * @param cust
     *     the customer to allow the user to select orders from
     */
    SelectOrder(JFrame frame, Customer cust)
    {
        this.frame = frame;
        this.cust = cust;
    }

    /**
     * Prompts the user to select an order for a particular customer (triggered
     * by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of customer name **/
        ArrayList<Order> orders = cust.getOrders().toArray();
        this.order = (Order) JOptionPane
            .showInputDialog(frame, "Select the order", "Select the order",
                             messageType, null, orders.toArray(), null);
    }

    /**
     * Accessor for the selected order
     *
     * @return the selected order
     */
    Order getOrder()
    {
        return this.order;
    }
}
