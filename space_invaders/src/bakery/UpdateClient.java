package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a client updater which updates a customer based on
 * input from the user.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class UpdateClient implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Constructs an UpdateClient
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery to update items within
     */
    UpdateClient(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Performs the customer update action (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Options to perform */
        String[] options = { "Edit customer name", "Edit customer residence" };

        /** Select a customer */
        SelectCustomer selectCust = new SelectCustomer(this.frame, this.bakery);
        selectCust.actionPerformed(actionEvent);
        int custId = selectCust.getId();
        if (custId == -1)
        {
            return;
        }

        Customer client = bakery.getClients().get(custId);

        /** Handle selection of option to perform */
        String option = (String) JOptionPane
            .showInputDialog(frame, "How would you " +
                                 "like to edit " + client.getName() + "?",
                             "Select the edit", messageType, null, options,
                             options[0]);
        if (option == null)
        {
            return;
        }
        else if (option.equals(options[0]))
        {
            /** Update client's name */
            this.updateName(client);
        }
        else if (option.equals(options[1]))
        {
            /** Update the client's residence */
            this.updateResidence(client);
        }

        JOptionPane.showMessageDialog(this.frame, "The client edit was made "
            + "successfully");
    }

    /**
     * Handles the updating of a customer's name based on input from the user.
     *
     * @param client
     *     the customer to update
     */
    private void updateName(Customer client)
    {
        String name;
        do
        {
            name = JOptionPane
                .showInputDialog(frame, "Enter the cutomer's last name" +
                                     " (at " + "least 3 characters)",
                                 client.getName());
            if (name == null)
            {
                return;
            }
            name = name.trim();
        }
        while (name.length() < 3);

        client.setName(name);
    }

    /**
     * Handles the updating of a customer's residence (name, address, city,
     * state, and zip) based on input from the user.
     *
     * @param client
     *     the customer to update
     */
    private void updateResidence(Customer client)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of customer address **/
        String address;
        do
        {
            address = JOptionPane.showInputDialog(frame,
                                                  "Enter the customer's street "
                                                      + "address",
                                                  client.getAddress());

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
                .showInputDialog(frame, "Enter the customer's city",
                                 client.getCity());
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
                             bakery.getStates(), client.getState());

        /** Handle input of customer zip code **/
        String zipMessage = "Enter the customer's zip code";
        int zip = -1;
        while (zip < 0)
        {
            String zipS;
            try
            {
                zipS = JOptionPane
                    .showInputDialog(frame, zipMessage, client.getZip());
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

        client.setAddress(address);
        client.setCity(city);
        client.setState(state);
        client.setZip(zip);
    }
}
