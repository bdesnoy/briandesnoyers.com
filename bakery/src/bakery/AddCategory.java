package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a category adder that allows the user to add a
 * category
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class AddCategory implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;
    private String name;

    /**
     * Constructs a category adder
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    AddCategory(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to add a category (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Handle input of new category **/
        String iName;
        do
        {
            iName = JOptionPane
                .showInputDialog(frame, "Enter the name of the new  " +
                    "category " + "(at least 3 " +
                    "characters)");
            if (iName == null)
            {
                return;
            }
            iName = iName.trim();
        }
        while (iName.length() < 3);

        if (this.bakery.getItemCategories().contains(iName))
        {
            JOptionPane.showMessageDialog(this.frame, "The category " + name
                + " already exists in this " +
                "bakery. It was not re-added.");
        }
        else
        {
            bakery.getItemCategories().add(iName);
            JOptionPane.showMessageDialog(this.frame, "The category " + name
                + " was added successfully.");
        }
        this.name = iName;
    }

    /**
     * Accessor for the name of the added category
     *
     * @return the name of the added category
     */
    String getName()
    {
        return this.name;
    }
}
