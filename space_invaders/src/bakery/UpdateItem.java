package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents an item updater which updates an item based on input
 * from the user
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-14
 */
class UpdateItem implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Constructs an UpdateItem
     *
     * @param frame
     *     the frame
     * @param bakery
     *     the bakery to update items within
     */
    UpdateItem(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Performs the item update action
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Options to perform */
        String[] options = { "Edit item name", "Edit item category",
            "Edit item price" };

        /** Select an item */
        SelectItem selectItem = new SelectItem(this.frame, this.bakery);
        selectItem.actionPerformed(actionEvent);
        int itemId = selectItem.getId();
        if (itemId == -1)
        {
            return;
        }

        BakeryItem item = bakery.getItems().get(itemId);

        /** Handle selection of option to perform */
        String option = (String) JOptionPane
            .showInputDialog(frame, "How would you " +
                                 "like to edit " + item.getName() + "?",
                             "Select the edit", messageType, null, options,
                             options[0]);
        if (option == null)
        {
            return;
        }
        else if (option.equals(options[0]))
        {
            /** Edit item name */
            this.changeName(item);
        }
        else if (option.equals(options[1]))
        {
            /** Edit item category */
            this.changeCategory(item, actionEvent);
        }
        else if (option.equals(options[2]))
        {
            /** Edit item price */
            this.changePrice(item);
        }

        JOptionPane.showMessageDialog(this.frame, "The item edit was made "
            + "successfully");
    }

    /**
     * Handles changing of the item item 's name
     *
     * @param item
     *     the item
     */
    private void changeName(BakeryItem item)
    {
        String name;
        do
        {
            name = JOptionPane.showInputDialog(this.frame,
                                               "Enter the bakery item name "
                                                   + "(at least 3 characters)",
                                               item.getName());
            if (name == null)
            {
                return;
            }
            name = name.trim();
        }
        while (name.length() < 4);
        item.setName(name);
    }

    /**
     * Handles changing of the item item 's category
     *
     * @param item
     *     the item
     * @param actionEvent
     *     the ActionEvent
     */
    private void changeCategory(BakeryItem item, ActionEvent actionEvent)
    {
        ArrayList<String> cats = new ArrayList<String>(
            bakery.getItemCategories());
        cats.add("Add a new category");
        String category;
        do
        {

            category = (String) JOptionPane
                .showInputDialog(frame, "Enter the item " +
                                     "category (at least 3 " + "characters)",
                                 "Enter the item category",
                                 JOptionPane.PLAIN_MESSAGE, null,
                                 cats.toArray(), item.getCategory());
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
        item.setCategory(category);
    }

    /**
     * Handles changing of the item item 's price
     *
     * @param item
     *     the item
     */
    private void changePrice(BakeryItem item)
    {
        /** Handle input of bakery item price **/
        String priceMessage = "Enter the bakery item price";
        double price = -1;
        while (price < 0)
        {
            String priceS;
            try
            {
                priceS = JOptionPane
                    .showInputDialog(this.frame, priceMessage, "");
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
        item.setPrice(price);
    }
}
