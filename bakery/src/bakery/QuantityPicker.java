package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a quantity picker that allows the user to select a
 * quantity. The selected quantity is available via the getQuantity method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class QuantityPicker implements ActionListener
{
    private JFrame frame;
    private int quantity = -1;
    private String message;
    private Integer original;

    /**
     * Constructs a new quantity picker
     *
     * @param frame
     *     the parent frame
     * @param message
     *     the message to prompt the user with
     */
    QuantityPicker(JFrame frame, String message)
    {
        this.frame = frame;
        this.message = message;
        this.original = null;
    }

    /**
     * Constructs a new quantity picker
     *
     * @param frame
     *     the parent frame
     * @param message
     *     the message to prompt the user with
     * @param orig
     *     the default quantity to show in the picker
     */
    QuantityPicker(JFrame frame, String message, Integer orig)
    {
        this.frame = frame;
        this.message = message;
        this.original = orig;
    }

    /**
     * Allow the user to select a quantity (triggered by an ActionEvent).
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Enter the quantity */
        String quantMessage = "Enter the " + message;
        int quant = -1;
        while (quant < 0)
        {
            String quantS;
            try
            {
                quantS = JOptionPane
                    .showInputDialog(frame, quantMessage, this.original);
                if (quantS == null)
                {
                    return;
                }
                quantS = quantS.trim();
                {
                    quant = Integer.parseInt(quantS);
                    quantMessage =
                        "You entered an invalid " + message + ". Please enter" +
                            " a " + "valid " + message + ".";
                }
            }
            catch (Exception e)
            {
                quant = -1;
                quantMessage =
                    "You entered an invalid " + message + ". Please enter" +
                        " a " + "valid " + message + ".";
            }
        }
        this.quantity = quant;
    }

    /**
     * Accessor for the quantity picked
     *
     * @return the quantity picked, -1 if no quantity was picked
     */
    int getQuantity()
    {
        return this.quantity;
    }
}
