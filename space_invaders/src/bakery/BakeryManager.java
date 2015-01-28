package bakery;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main GUI for the Bakery Manager program.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
public class BakeryManager
{

    /**
     * Launch the user interface for a new bakery state
     *
     * @param args
     *     the command line arguments
     */
    public static void main(String[] args)
    {
        Bakery bakery = new Bakery();
        new BakeryManager().startGUI(bakery);
    }

    /**
     * Starts a GUI with the bakery state bakery
     *
     * @param bakery
     *     the bakery state
     */
    private void startGUI(Bakery bakery)
    {
        JFrame mainFrame = new JFrame("Bakery Manager");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainFrame.getContentPane().add(mainPanel);
        GridLayout layout = new GridLayout(4, 3);
        mainPanel.setLayout(layout);

        mainPanel.add(new JLabel("Item Management"));
        mainPanel.add(new JLabel("Customer Management"));
        mainPanel.add(new JLabel("Bakery Management"));

        JButton addItemCat = new JButton("Add Item Category");
        addItemCat.addActionListener(new AddCategory(mainFrame, bakery));
        mainPanel.add(addItemCat);

        JButton addCustomer = new JButton("Add Customer");
        addCustomer.addActionListener(new AddClient(mainFrame, bakery));
        mainPanel.add(addCustomer);

        JButton addOrder = new JButton("Add Order");
        addOrder.addActionListener(new AddOrder(mainFrame, bakery));
        mainPanel.add(addOrder);

        JButton addItem = new JButton("Add Item");
        addItem.addActionListener(new AddItem(mainFrame, bakery));
        mainPanel.add(addItem);

        JButton editCustomer = new JButton("Edit Customer");
        editCustomer.addActionListener(new UpdateClient(mainFrame, bakery));
        mainPanel.add(editCustomer);

        JButton editOrder = new JButton("Edit Order");
        editOrder.addActionListener(new UpdateOrder(mainFrame, bakery));
        mainPanel.add(editOrder);

        JButton editItem = new JButton("Edit Item");
        editItem.addActionListener(new UpdateItem(mainFrame, bakery));
        mainPanel.add(editItem);

        JButton showCustomerInformation = new JButton(
            "Show Customer " + "Information");
        showCustomerInformation
            .addActionListener(new ShowCustomerInformation(mainFrame, bakery));
        mainPanel.add(showCustomerInformation);

        JButton printedReports = new JButton("Printed Reports");
        printedReports.addActionListener(new PrintedReports(mainFrame, bakery));
        mainPanel.add(printedReports);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem importRec = new JMenuItem("Import Bakery Records");
        importRec.addActionListener(new ImportFromFiles(mainFrame, bakery));
        file.add(importRec);
        JMenuItem export = new JMenuItem("Export to Bakery Records");
        export.addActionListener(new ExportToFiles(mainFrame, bakery));
        file.add(export);

        JMenu help = new JMenu("Help");
        menuBar.add(help);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new About(mainFrame));
        help.add(about);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
