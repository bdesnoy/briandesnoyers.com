package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents an OrderItem selector that allows the user to select an
 * OrderItem
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class SelectOrderItem implements ActionListener
{
    private JFrame frame;
    private Order order;
    private OrderItem item;

    /**
     * Constructor for SelectOrderItem
     *
     * @param frame
     *     the parent frame
     * @param order
     *     the Order
     */
    SelectOrderItem(JFrame frame, Order order)
    {
        this.frame = frame;
        this.order = order;
    }

    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of customer name **/
        ArrayList<OrderItem> items = this.order.getItems();
        this.item = (OrderItem) JOptionPane
            .showInputDialog(frame, "Select the item", "Select the item",
                             messageType, null, items.toArray(), null);
    }

    /**
     * The OrderItem that has been selected
     *
     * @return the OrderItem that has been selected
     */
    OrderItem getItem()
    {
        return this.item;
    }
}
