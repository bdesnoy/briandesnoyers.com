package bakery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a date selector that allows the user to select a date
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
class SelectDate implements ActionListener
{
    private JFrame frame;
    private String prompt;
    private BakeryDate date;

    /**
     * Constructor for SelectDate
     *
     * @param frame
     *     the parent frame
     * @param prompt
     *     the prompt
     */
    SelectDate(JFrame frame, String prompt)
    {
        this.frame = frame;
        this.prompt = prompt;
    }

    /**
     * Prompt the user to select a date (triggered by an ActionEvent)
     *
     * @param actionEvent
     *     the ActionEvent
     */
    @Override public void actionPerformed(ActionEvent actionEvent)
    {
        /** Set up message type */
        int messageType = JOptionPane.PLAIN_MESSAGE;

        /** Set up the options */
        String[] months = { "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November",
            "December" };
        Integer[] years = new Integer[100];
        for (int i = 0; i < 100; i++)
        {
            years[i] = i;
        }
        Integer[] dates = new Integer[31];
        for (int i = 0; i < 31; i++)
        {
            dates[i] = i + 1;
        }

        /** Handle input of month **/
        int m;
        String month = (String) JOptionPane
            .showInputDialog(frame, "Select the " + this.prompt + " month",
                             "Select the month", messageType, null, months,
                             new SimpleDateFormat("MMMM").format(new Date()));
        if (month == null)
        {
            this.date = null;
            return;
        }
        else
        {
            m = getMonth(month);
        }

        /** Handle input of date */
        Integer d = (Integer) JOptionPane
            .showInputDialog(frame, "Select the " + this.prompt + " date",
                             "Select the date", messageType, null, dates,
                             Integer.parseInt(new SimpleDateFormat("dd")
                                                  .format(new Date())));
        if (d == null)
        {
            this.date = null;
            return;
        }

        /** Handle input of year */
        Integer y = (Integer) JOptionPane
            .showInputDialog(frame, "Select the " + this.prompt + " year",
                             "Select the year", messageType, null, years,
                             Integer.parseInt(new SimpleDateFormat("yy")
                                                  .format(new Date())));
        if (y == null)
        {
            this.date = null;
            return;
        }

        this.date = new BakeryDate(m, d, y);
    }

    /**
     * The selected date
     *
     * @return the selected date
     */
    BakeryDate getDate()
    {
        return this.date;
    }

    /**
     * Convert the given String into an int represent a month
     *
     * @param month
     *     the given String
     * @return an int representing the given String as a month
     */
    static int getMonth(String month)
    {
        if (month.equals("January"))
        {
            return 1;
        }
        else if (month.equals("February"))
        {
            return 2;
        }
        else if (month.equals("March"))
        {
            return 3;
        }
        else if (month.equals("April"))
        {
            return 4;
        }
        else if (month.equals("May"))
        {
            return 5;
        }
        else if (month.equals("June"))
        {
            return 6;
        }
        else if (month.equals("July"))
        {
            return 7;
        }
        else if (month.equals("August"))
        {
            return 8;
        }
        else if (month.equals("September"))
        {
            return 9;
        }
        else if (month.equals("October"))
        {
            return 10;
        }
        else if (month.equals("November"))
        {
            return 11;
        }
        else if (month.equals("December"))
        {
            return 12;
        }
        else
        {
            throw new IllegalArgumentException("not valid month");
        }
    }
}
