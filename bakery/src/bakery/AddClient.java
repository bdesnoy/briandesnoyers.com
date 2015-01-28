package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a customer adder that allows the user to add a new
 * customer. The added customer's id can by accessed via the getId method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class AddClient implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;
    private int id = -1;

    /**
     * Constructs a new customer adder
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state to add the customer to
     */
    AddClient(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to add a customer (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of customer name **/
        String name;
        do
        {
            name = JOptionPane
                .showInputDialog(frame, "Enter the cutomer's last name" +
                    " (at " + "least 3 characters)");
            if (name == null)
            {
                return;
            }
            name = name.trim();
        }
        while (name.length() < 3);

        /** Handle input of customer address **/
        String address;
        do
        {
            address = JOptionPane
                .showInputDialog(frame, "Enter the customer's street address");

            if (address == null)
            {
                return;
            }
            address = address.trim();
        }
        while (address.length() < 4);

        /** Handle input of customer city **/
        String city;
        do
        {
            city = JOptionPane
                .showInputDialog(frame, "Enter the customer's city");
            if (city == null)
            {
                return;
            }
            city = city.trim();
        }
        while (city.length() < 4);

        /** Handle input of customer state **/
        String state = (String) JOptionPane
            .showInputDialog(frame, "Select the customer's state",
                             "Select the customer's state", messageType, null,
                             bakery.getStates(), bakery.getDefaultState());
        if (state == null)
        {
            return;
        }

        /** Handle input of customer zip code **/
        String zipMessage = "Enter the customer's zip code";
        int zip = -1;
        while (zip < 0)
        {
            String zipS;
            try
            {
                zipS = JOptionPane.showInputDialog(frame, zipMessage);
                if (zipS == null)
                {
                    return;
                }
                zipS = zipS.trim();
                if (zipS.length() == 5)
                {
                    zip = Integer.parseInt(zipS);
                }
                else
                {
                    zipMessage = "You entered an invalid zip code. Valid zip " +
                        "codes are numerics of length 5. Please enter a valid" +
                        " zip code.";
                }
            }
            catch (Exception e)
            {
                zip = -1;
                zipMessage = "You entered an invalid zip code. Valid zip " +
                    "codes are numerics of length 5. Please enter a valid" +
                    " zip code.";
            }
        }

        this.id = bakery.getClients()
            .add(new Customer(name, address, city, state, zip));
    }

    /**
     * Returns the id of the added customer or -1 if no customer was added (i.e.
     * if the user canceled during the prompt).
     *
     * @return the id of the added customer
     */
    int getId()
    {
        return this.id;
    }
}
