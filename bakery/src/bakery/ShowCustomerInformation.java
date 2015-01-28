package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * This class represents a customer information accessor that shows the user
 * customer information about a particular customer based on a customer
 * selection from the user.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class ShowCustomerInformation implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Constructs a new customer information accessor
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    ShowCustomerInformation(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Shows the user customer information about a particular customer based on
     * a customer selection from the user (triggered by an ActionEvent)
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

        DecimalFormat money = new DecimalFormat("$0.00");
        DecimalFormat zip = new DecimalFormat("00000");
        Customer cust = bakery.getClients().get(custId);
        String info = "";
        info += "Name: " + cust.getName() + "\n";
        info += "Address: " + cust.getAddress() + "\n";
        info += "City/State: " + cust.getCity() + ", " + cust.getState() + " " +
            zip.format(cust.getZip()) + "\n";
        info +=
            "Loyalty Card Balance: " + money.format(cust.getDiscount()) + "\n";
        info +=
            "Amount until Next Reward " + money.format(100 - cust.getLoyalty())
                + "\n";
        JOptionPane.showMessageDialog(frame, info, "Customer Information",
                                      JOptionPane.PLAIN_MESSAGE);
    }
}
