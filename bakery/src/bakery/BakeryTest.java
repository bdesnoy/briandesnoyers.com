package bakery;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * White box testing for bakery state
 *
 * @author Brian Desnoyers bdesnoy@ccs.neu.edu
 * @version 2014-06-13
 */
public class BakeryTest extends TestCase
{
    private Customer lucas;
    private Customer brian;
    private OrderItem weddingCake;
    private BakeryDate june15th;
    private BakeryDate june16th;
    private BakeryItem blueberryMuffin;
    private Order order1;
    private IDList<Customer> idlist1;
    private ItemList itemList1;
    private Bakery bakery1;
    private CustomerList custList1;
    private Order order2;
    private ItemList itemList2;

    /**
     * Set up the tests
     */
    public void setUp()
    {
        lucas = new Customer("Magee", "319 Huntington Ave.", "Boston", "MA",
                             2115);
        brian = new Customer("Desnoyers", "319 Huntington Ave.", "Boston",
                             "MA", 2115);
        weddingCake = new OrderItem(1, 8, "Wedding Cake", "Cake", 199.99);
        june15th = new BakeryDate(6, 15, 14);
        blueberryMuffin = new BakeryItem("Muffin", "Blueberry Muffin", 2.50);
        order1 = new Order(1, june15th, june15th);
        june16th = new BakeryDate(6, 16, 14);
        idlist1 = new IDList<Customer>();
        itemList1 = new ItemList();
        bakery1 = new Bakery();
        custList1 = new CustomerList();
        order2 = new Order(2, june15th, june16th);
        itemList2 = new ItemList();
    }

    /**
     * Test the BakeryDate method
     */
    public void testBakeryDate()
    {

        assertEquals("BakeryDate from String",
                     BakeryDate.toBakeryDate("2/5/10"),
                     new BakeryDate(2, 5, 10));
        assertEquals("BakeryDate from String",
                     BakeryDate.toBakeryDate("02/5/10"),
                     new BakeryDate(2, 5, 10));
        assertEquals("BakeryDate from String",
                     BakeryDate.toBakeryDate("2/05/10"),
                     new BakeryDate(2, 5, 10));
        assertEquals("BakeryDate from String",
                     BakeryDate.toBakeryDate("02/05/10"),
                     new BakeryDate(2, 5, 10));
        assertEquals("BakeryDate from String",
                     BakeryDate.toBakeryDate("2/5/05"),
                     new BakeryDate(2, 5, 5));

        assertTrue(BakeryDate.isDate(1));
        assertTrue(BakeryDate.isDate(31));
        assertFalse(BakeryDate.isDate(0));
        assertFalse(BakeryDate.isDate(32));
        assertTrue(BakeryDate.isMonth(1));
        assertTrue(BakeryDate.isMonth(12));
        assertFalse(BakeryDate.isMonth(0));
        assertFalse(BakeryDate.isMonth(13));
        assertTrue(BakeryDate.isYear(0));
        assertTrue(BakeryDate.isYear(99));
        assertFalse(BakeryDate.isYear(-1));
        assertFalse(BakeryDate.isYear(100));
        assertEquals(june15th.toString(), "6/15/14");
        assertEquals(june15th.hashCode(), 61514);
        assertFalse(june15th.equals(null));
        assertFalse(june15th.equals(lucas));
        assertFalse(june15th.equals(june16th));

        try
        {
            new BakeryDate(13, 29, 14);
            assertTrue("Invalid date created", false);
        }
        catch (IllegalArgumentException e)
        {
            assertTrue("Invalid date- correct exception thrown", true);
        }
        catch (Exception e)
        {
            assertTrue("Invalid date- incorrect exception thrown", false);
        }
    }

    /**
     * Test Customer implementation
     */
    public void testCustomer()
    {
        // add additional tests
        //Test methods within Customer class
        assertEquals(lucas.getAddress(), "319 Huntington Ave.");
        assertEquals(lucas.getCity(), "Boston");
        assertEquals(lucas.getDiscount(), 0.0);
        assertEquals(lucas.getLoyalty(), 0.0);
        assertEquals(lucas.getName(), "Magee");
        assertEquals(lucas.getState(), "MA");
        lucas.addToLoyalty(104);
        assertEquals(lucas.getDiscount(), 10.0);
        assertEquals(lucas.getLoyalty(), 4.0);
        order1.setPaid(true);
        lucas.addToLoyalty(order1);
        lucas.removeFromLoyalty(order1);
        assertEquals(lucas.getDiscount(), 10.0);
        assertEquals(lucas.getLoyalty(), 4.0);
        order1.setPaid(false);
    }

    /**
     * Test OrderItem implementation
     */
    public void testOrderItem()
    {
        //test methods within OrderItem
        assertEquals(weddingCake.toString(), "8x Wedding Cake Cake = $1599.92");
        assertEquals(weddingCake.getName(), "Wedding Cake");
        assertEquals(weddingCake.getCategory(), "Cake");
        assertEquals(weddingCake.getId(), 1);
        assertEquals(weddingCake.getPrice(), 1599.92);
        assertEquals(weddingCake.getQuantity(), 8);
        weddingCake.setId(1);
        assertEquals(weddingCake.getId(), 1);
        weddingCake.setQuantity(8);
        assertEquals(weddingCake.getQuantity(), 8);
        assertEquals(weddingCake.getPrice(), 1599.92);
        weddingCake.setName("Wedding Cake");
        assertEquals(weddingCake.getName(), "Wedding Cake");
        weddingCake.setCategory("Cake");
        assertEquals(weddingCake.getCategory(), "Cake");

    }

    /**
     * Test BakeryItem implementation
     */
    public void testBakeryItem()
    {
        //test methods within BakeryItem
        blueberryMuffin.setCategory("Muffin");
        blueberryMuffin.setName("Blueberry Muffin");
        blueberryMuffin.setPrice(2.5);
        assertEquals(blueberryMuffin.getName(), "Blueberry Muffin");
        assertEquals(blueberryMuffin.getCategory(), "Muffin");
        assertEquals(blueberryMuffin.getPrice(), 2.50);
        assertEquals(blueberryMuffin.toString(), "Blueberry Muffin Muffin");

    }

    /**
     * Test Order implementation
     */
    public void testOrder()
    {
        //test methods within Order
        itemList2.add(1, blueberryMuffin);
        ArrayList<OrderItem> orderItems1 = new ArrayList<OrderItem>();
        orderItems1.add(weddingCake);
        order1.setItems(orderItems1);
        order1.setOrdered(june15th);
        order1.setDiscountUsed(0.0);
        assertEquals(order1.getId(), 1);
        assertEquals(order1.getOrdered(), june15th);
        assertEquals(order1.getPickup(), june15th);
        assertEquals(order1.getDiscountUsed(), 0.0);
        assertEquals(order1.getItems(), orderItems1);
        assertFalse(order1.isPaid());
        assertEquals(order1.getSubTotal(), 1599.92);
        assertEquals(order1.getTotal(), 1599.92);
        assertEquals(order1.toString(),
                     "1 " + june15th + " $1599.92 Unpaid");
        order1.setId(2);
        assertEquals(order1.getId(), 2);
        order1.setPaid(true);
        assertTrue(order1.isPaid());
        order1.setPickup(june16th);
        assertEquals(order1.getPickup(), june16th);
        assertTrue(order1.contains(1));
        assertFalse(order1.contains(2));
        assertEquals(Order.cutTo("test", 3), "tes");
        assertEquals(order1.toString(),
                     "2 " + june15th + " $1599.92 Paid");
        order2.addItem(1, 3, itemList2);
        assertTrue(order2.contains(1));
        assertEquals(order2.getTotal(), 7.50);
        order2.addItem(2, 2, "Bagel", "Bagel", 1.35);
        assertTrue(order2.contains(2));

    }

    /**
     * Test IDList implementation
     */
    public void testIDList()
    {
        TreeMap<Integer, Customer> tmap = new TreeMap<Integer,
            Customer>();
        assertEquals(idlist1.getContents(), tmap);
        idlist1.add(1, lucas);
        assertEquals(idlist1.get(1), lucas);
        ArrayList<Customer> clist = new ArrayList<Customer>();
        clist.add(lucas);
        assertEquals(idlist1.toArray(), clist);
        assertTrue(idlist1.contains(1));
        assertFalse(idlist1.contains(2));
        assertEquals(idlist1.add(brian), 2);
    }

    /**
     * Test Bakery implementation
     */
    public void testBakery()
    {
        bakery1.setItems(new ItemList());
        assertEquals(Bakery.getDiscountAmt(), 10.0);
        assertEquals(Bakery.getDiscountFrequency(), 100.0);
        assertEquals(bakery1.getItems().getContents(),
                     new ItemList().getContents());
        assertEquals(bakery1.getClients().getContents(),
                     new CustomerList().getContents());
        assertEquals(bakery1.getDefaultState(), "NC");
        assertEquals(bakery1.getItemCategories(), new ArrayList<String>());
        assertEquals(56, bakery1.getStates().length);
    }

    /**
     * Test CustomerList implementation
     */
    public void testCustomerList() {
        OrderList olist = new OrderList();
        olist.add(1, order1);
        lucas.setOrders(olist);
        custList1.add(1, lucas);
        custList1.add(2, brian);
        assertEquals(custList1.getNextOrderId(), 2);
        ArrayList<String> custStringList = new ArrayList<String>();
        custStringList.add("1" + "\t" + "Magee 02115");
        custStringList.add("2" + "\t" + "Desnoyers 02115");
        assertEquals(custList1.customerListString(), custStringList);

    }

    /**
     * Test ItemList implementation
     */
    public void testItemList()
    {
        itemList1.add(1, blueberryMuffin);
        ArrayList<String> ilist = new ArrayList<String>();
        ilist.add("1" + "\t" + "Blueberry Muffin Muffin $2.50");
        assertEquals(itemList1.itemListString(), ilist);

    }
}