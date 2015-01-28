package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents a file importer that initializes the bakery state from
 * files based on interaction from the user.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-15
 */
class ImportFromFiles implements ActionListener
{
    private JFrame frame;
    private Bakery bakery;

    /**
     * Construct a new file importer.
     *
     * @param frame
     *     the parent frame
     * @param bakery
     *     the bakery state
     */
    ImportFromFiles(JFrame frame, Bakery bakery)
    {
        this.frame = frame;
        this.bakery = bakery;
    }

    /**
     * Initialize the bakery state based on interaction from the user (triggered
     * by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        String itemsPath = "bakeryItems.txt";
        File itemsFile = new File(itemsPath);
        Scanner itemsInput;
        String ordersPath = "orders.txt";
        File ordersFile = new File(ordersPath);
        Scanner ordersInput;

        JOptionPane
            .showMessageDialog(this.frame, "The import will be read from " +
                itemsPath + " and\n" + ordersPath + " in " +
                "this application's directory.\n" +
                "Please be sure that those files exist\n" +
                "in that directory before continuing. \nSelect okay" +
                " to continue.");

        try
        {
            itemsInput = new Scanner(itemsFile);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this.frame, "The file " +
                itemsPath + " was not found.");
            return;
        }

        try
        {
            ordersInput = new Scanner(ordersFile);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this.frame, "The file " +
                ordersPath + " was not found.");
            return;
        }

        int numItems = 0;
        try
        {
            numItems = this.readItems(itemsInput);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this.frame, "The file " +
                itemsPath + " is in the incorrect format.");
        }
        itemsInput.close();

        int numOrders = 0;
        try
        {
            numOrders = this.readOrders(ordersInput);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this.frame, "The file " +
                ordersPath + " is in the incorrect format.");
        }
        ordersInput.close();

        JOptionPane.showMessageDialog(this.frame, numItems + " items and " +
            numOrders + " order items were added.\nIf this is incorrect, " +
            "please double-check the files.");
    }

    /**
     * Read the items in from the items file
     *
     * @param input
     *     the Scanner tied to the items input file
     * @return the number of items imported
     */
    private int readItems(Scanner input)
    {
        int numItems = 0;
        if (!input.hasNextLine())
        {
            return numItems;
        }
        input.nextLine();
        input.useDelimiter("\t|\n");
        while (input.hasNextLine())
        {
            int id;
            String name;
            String category;
            double price;
            if (input.hasNextInt())
            {
                id = input.nextInt();
            }
            else
            {
                break;
            }
            if (input.hasNext())
            {
                name = input.next();
            }
            else
            {
                break;
            }
            if (input.hasNext())
            {
                category = input.next();
            }
            else
            {
                break;
            }
            if (input.hasNextDouble())
            {
                price = input.nextDouble();
            }
            else
            {
                break;
            }
            this.bakery.getItems()
                .add(id, new BakeryItem(category, name, price));
            numItems++;
        }
        return numItems;
    }

    /**
     * Read the orders from the orders file
     *
     * @param input
     *     the Scanner tied to the orders input file
     * @return the number of orders imported
     */
    private int readOrders(Scanner input)
    {
        int ordersAdded = 0;

        if (!input.hasNextLine())
        {
            return ordersAdded;
        }
        input.nextLine();
        input.useDelimiter("\t|\n");
        while (input.hasNextLine())
        {
            int custId;
            if (input.hasNextInt())
            {
                custId = input.nextInt();
            }
            else
            {
                break;
            }

            String custName;
            if (input.hasNext())
            {
                custName = input.next();
            }
            else
            {
                break;
            }

            String custAddress;
            if (input.hasNext())
            {
                custAddress = input.next();
            }
            else
            {
                break;
            }

            String custCity;
            if (input.hasNext())
            {
                custCity = input.next();
            }
            else
            {
                break;
            }

            String custState;
            if (input.hasNext())
            {
                custState = input.next();
            }
            else
            {
                break;
            }

            int custZip;
            if (input.hasNextInt())
            {
                custZip = input.nextInt();
            }
            else
            {
                break;
            }

            if (!this.bakery.getClients().contains(custId))
            {
                this.bakery.getClients().add(custId,
                                             new Customer(custName, custAddress,
                                                          custCity, custState,
                                                          custZip));
            }
            Customer cust = this.bakery.getClients().get(custId);

            int orderId;
            if (input.hasNextInt())
            {
                orderId = input.nextInt();
            }
            else
            {
                break;
            }

            boolean paid;
            if (input.hasNext())
            {
                paid = input.next().trim().equalsIgnoreCase("Yes");
            }
            else
            {
                break;
            }

            BakeryDate orderDate;
            if (input.hasNext())
            {
                orderDate = BakeryDate.toBakeryDate(input.next());
            }
            else
            {
                break;
            }

            BakeryDate pickupDate;
            if (input.hasNext())
            {
                pickupDate = BakeryDate.toBakeryDate(input.next());
            }
            else
            {
                break;
            }

            int itemId;
            if (input.hasNextInt())
            {
                itemId = input.nextInt();
            }
            else
            {
                break;
            }

            String itemName;
            if (input.hasNext())
            {
                itemName = input.next();
            }
            else
            {
                break;
            }

            String itemCategory;
            if (input.hasNext())
            {
                itemCategory = input.next();
            }
            else
            {
                break;
            }

            int quantity;
            if (input.hasNextInt())
            {
                quantity = input.nextInt();
            }
            else
            {
                break;
            }

            double itemPrice;
            if (input.hasNextDouble())
            {
                itemPrice = input.nextDouble();
            }
            else
            {
                break;
            }

            if (input.hasNextDouble())
            {
                input.nextDouble(); // item total
            }
            else
            {
                break;
            }

            double discountUsed;
            if (input.hasNextDouble())
            {
                discountUsed = input.nextDouble();
                if (discountUsed != 0)
                {
                    discountUsed = discountUsed * -1;
                }
            }
            else
            {
                break;
            }

            if (input.hasNextDouble())
            {
                input.nextDouble(); // total due
            }
            else
            {
                break;
            }

            double availDisc;
            if (input.hasNextDouble())
            {
                availDisc = input.nextDouble();
            }
            else
            {
                break;
            }

            double curLoyalty;
            if (input.hasNextDouble())
            {
                curLoyalty = input.nextDouble();
            }
            else
            {
                break;
            }

            if (!cust.getOrders().contains(orderId))
            {
                cust.getOrders()
                    .add(orderId, new Order(orderId, orderDate, pickupDate));
            }
            Order order = cust.getOrders().get(orderId);

            if (!this.bakery.getItemCategories().contains(itemCategory))
            {
                this.bakery.getItemCategories().add(itemCategory);
            }

            order.addItem(itemId, quantity, itemName, itemCategory, itemPrice);
            order.setDiscountUsed(discountUsed);
            cust.setDiscount(availDisc);
            cust.setLoyalty(curLoyalty);

            if (paid)
            {
                order.setPaid(true);
            }
            ordersAdded++;
        }
        return ordersAdded;
    }
}
