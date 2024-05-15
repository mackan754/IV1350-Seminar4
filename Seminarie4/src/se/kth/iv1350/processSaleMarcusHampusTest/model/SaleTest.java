package se.kth.iv1350.processSaleMarcusHampusTest.model;

import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDetails;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SaleTest {

    private Sale sale;
    private ItemDetails itemDTO1;
    private ItemDetails itemDTO2;

    @Before
    public void setUp() {
        sale = new Sale();
        itemDTO1 = new ItemDetails("Apple", "Fresh apple", new Amount(10), new Amount(2));
        itemDTO2 = new ItemDetails("Banana", "Fresh banana", new Amount(15), new Amount(3));
    }

    @Test
    public void testGetTotal() {
        assertEquals("Total should be 0 initially.", 0, sale.getTotal().getAmount(), 0);
    }

    @Test
    public void testGetTotalIncludingTax() {
        assertEquals("Total including tax should be 0 initially.", 0, sale.getTotalIncludingTax().getAmount(), 0);
    }

    @Test
    public void testGetItems() {
        ArrayList<Item> items = sale.getItems();
        assertEquals("Item list should be empty initally", 0, items.size());
    }

    @Test
    public void testGetSaleTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime saleTime = sale.getSaleTime();

        assertEquals("Sale time should match the current time.", currentTime.getYear(), saleTime.getYear());
        assertEquals("Sale time should match the current time.", currentTime.getMonth(), saleTime.getMonth());
        assertEquals("Sale time should match the current time.", currentTime.getDayOfMonth(), saleTime.getDayOfMonth());
        assertEquals("Sale time should match the current time.", currentTime.getHour(), saleTime.getHour());
        assertEquals("Sale time should match the current time.", currentTime.getMinute(), saleTime.getMinute());
    }

    @Test
    public void testAddItem() {
        Item item1 = new Item("123", itemDTO1, new Amount(0));
        Item item2 = new Item("456", itemDTO2, new Amount(0));

        sale.addItem(item1, new Amount(5));
        assertEquals("One item should be added", 1, sale.getItems().size());
        assertEquals("Total amount should be updated correctly", 50, sale.getTotal().getAmount(), 0);

        sale.addItem(item2, new Amount(3));
        assertEquals("Another item should be added", 2, sale.getItems().size());
        assertEquals("Total amount should be updated correctly", 95, sale.getTotal().getAmount(), 0);

        sale.addItem(item1, new Amount(5));
        assertEquals("Duplicate item should not increase item count", 2, sale.getItems().size());
        assertEquals("Total amount should be updated correctly", 145, sale.getTotal().getAmount(), 0);
    }
}
