package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents an item adder that allows the user to add a new Item.
 * The added item's id can be accessed via the getId method.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
@SuppressWarnings("unchecked") class AddItem implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;
    private int id = -1;

    /**
     * Constructor for AddItem
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state to add the item to
     */
    AddItem(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Prompt the user to add an Item (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Handle input of item name **/
        String name;
        do
        {
            name = JOptionPane
                .showInputDialog(frame, "Enter the bakery item name " +
                                     "(at least" + " 4 characters)",
                                 "Enter the bakery item name", messageType);
            if (name == null)
            {
                return;
            }
            name = name.trim();
        }
        while (name.length() < 4);

        /** Handle input of item category **/
        ArrayList<String> cats;
        cats = (ArrayList<String>) bakery.getItemCategories().clone();
        cats.add("Add a new category");
        String category;
        do
        {

            category = (String) JOptionPane
                .showInputDialog(frame, "Select the item " + "category",
                                 "Enter the item category", messageType, null,
                                 cats.toArray(), "Add a new category");
            if (category == null)
            {
                return;
            }
        }
        while (category.length() < 4);
        if (category.equals("Add a new category"))
        {
            AddCategory addcategory = new AddCategory(this.frame, bakery);
            addcategory.actionPerformed(actionEvent);
            category = addcategory.getName();
        }
        if (category == null)
        {
            return;
        }

        /** Handle input of bakery item price **/
        String priceMessage = "Enter the bakery item price";
        double price = -1;
        while (price < 0)
        {
            String priceS;
            try
            {
                priceS = JOptionPane.showInputDialog(frame, priceMessage, "");
                if (priceS == null)
                {
                    return;
                }
                priceS = priceS.trim();
                price = Double.parseDouble(priceS);
                priceMessage = "You entered an invalid price. Valid " +
                    "prices are positive numbers. Please enter a valid " +
                    "price.";
            }
            catch (Exception e)
            {
                price = -1;
                priceMessage = "You entered an invalid price. Valid " +
                    "prices are positive numbers. Please enter a valid " +
                    "price.";
            }
        }

        this.id = bakery.getItems().add(new BakeryItem(category, name, price));
    }

    /**
     * Returns the id of the added item
     *
     * @return the id of the added item
     */
    int getId()
    {
        return this.id;
    }
}
