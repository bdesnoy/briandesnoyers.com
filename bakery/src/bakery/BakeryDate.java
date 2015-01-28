package bakery;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a date (month, day, year) for the bakery.
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-10
 */
class BakeryDate extends Date
{
    /**
     * month
     */
    private int month;
    /**
     * day
     */
    private int date;
    /**
     * year
     */
    private int year;

    /**
     * Constructor for a BakeryDate of month/date/year
     *
     * @param month
     *     month in M or MM format
     * @param date
     *     day in D or DD format
     * @param year
     *     year in YY format
     */
    BakeryDate(int month, int date, int year)
    {
        if (isMonth(month) && isDate(date) && isDate(year))
        {
            this.month = month;
            this.date = date;
            this.year = year;
        }
        else
        {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    /**
     * Constructs a bakery date with today's current date
     */
    BakeryDate()
    {
        this.month = Integer
            .parseInt(new SimpleDateFormat("MM").format(new Date()));
        this.date = Integer
            .parseInt(new SimpleDateFormat("dd").format(new Date()));
        this.year = Integer
            .parseInt(new SimpleDateFormat("yy").format(new Date()));
    }

    /**
     * whether or not the given int represents a month
     *
     * @param m
     *     the int
     * @return whether or not the given int represent a month
     */
    public static boolean isMonth(int m)
    {
        return m < 13 && m > 0;
    }

    /**
     * whether or not the given int represents a day
     *
     * @param d
     *     the int
     * @return whether or not the given int represent a day
     */
    public static boolean isDate(int d)
    {
        return d > 0 && d < 32;
    }

    /**
     * whether or not the given int represents a year
     *
     * @param y
     *     the int
     * @return whether or not the given int represent a year
     */
    public static boolean isYear(int y)
    {
        return y > -1 && y < 100;
    }

    /**
     * A String representation of this BakeryDate
     *
     * @return a String representation of this BakeryDate
     */
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("##");
        return df.format(month) + "/" + df.format(date) + "/" + df.format(year);
    }

    /**
     * the hash code of this BakeryDate
     *
     * @return the hash code of this BakeryDate
     */
    public int hashCode()
    {
        DecimalFormat df = new DecimalFormat("##");
        return Integer
            .parseInt(df.format(month) + df.format(date) + df.format(year));
    }

    /**
     * Whether or not this BakeryDate is equal to the given Object
     *
     * @param o
     *     the given Object
     * @return Whether or not this BakeryDate is equal to the given Object
     */
    public boolean equals(Object o)
    {
        if (o instanceof BakeryDate)
        {
            BakeryDate that = (BakeryDate) o;
            return this.month == that.month && this.date == that.date
                && this.year == that.year;
        }
        else
        {
            return false;
        }
    }

    /**
     * Converts String s to a BakeryDate
     *
     * @param s
     *     the String to convert in MM/DD/YY format, M/D/YY format, MM/D/YY
     *     format, or M/DD/YY format
     * @return the converted BakeryDate
     */
    static BakeryDate toBakeryDate(String s)
    {
        int firstSlash = s.indexOf('/');
        int lastSlash = s.lastIndexOf('/');
        int month = Integer.parseInt(s.substring(0, firstSlash));
        int date = Integer.parseInt(s.substring(firstSlash + 1, lastSlash));
        int year = Integer.parseInt(s.substring(lastSlash + 1, s.length()));

        return new BakeryDate(month, date, year);
    }
}
