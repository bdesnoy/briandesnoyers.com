package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents a customer selector that allows the user to select a
 * customer or add a new customer. The added or selected customer's id is
 * accessible via the getId method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class SelectCustomer implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;
    private int id;

    /**
     * Constructs a new customer selector
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state to select/add customers within
     */
    SelectCustomer(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to select or add a customer (triggered by an
     * ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of customer name **/
        ArrayList<String> custs = bakery.getClients().customerListString();
        custs.add("Add a new customer");
        String cust = (String) JOptionPane
            .showInputDialog(frame, "Select the customer",
                             "Select the customer", messageType, null,
                             custs.toArray(), "Add a new customer");
        if (cust == null)
        {
            id = -1;
        }
        else if (cust.equals("Add a new customer"))
        {
            AddClient addclient = new AddClient(this.frame, bakery);
            addclient.actionPerformed(actionEvent);
            this.id = addclient.getId();
        }
        else
        {
            this.id = Integer.parseInt(cust.substring(0, cust.indexOf('\t')));
        }
    }

    /**
     * Accessor for the id of the selected or added customer
     *
     * @return the id of the selected or added customer
     */
    int getId()
    {
        return this.id;
    }
}
