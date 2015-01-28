package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents an item selector that allows the user to select an
 * item.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class SelectItem implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;
    private int id;

    /**
     * Constructor for SelectItem
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     bakery state an item is being selected from
     */
    SelectItem(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to select an item (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of item name **/
        ArrayList<String> itms = bakery.getItems().itemListString();
        String itm = (String) JOptionPane
            .showInputDialog(frame, "Select the item", "Customized Dialog",
                             messageType, null, itms.toArray(), null);
        if (itm == null || itm.equals("Select an item"))
        {
            id = -1;
        }
        else
        {
            this.id = Integer.parseInt(itm.substring(0, itm.indexOf('\t')));
        }
    }

    /**
     * Accessor for the id of the selected item
     *
     * @return the id of the selected Item
     */
    int getId()
    {
        return this.id;
    }
}
