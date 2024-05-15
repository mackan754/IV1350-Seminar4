package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDetails;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class ItemTest {

    private Item item;

    @BeforeEach
    public void setUp() {
        String itemIdentifier = "12345";
        ItemDetails itemInformation = new ItemDetails("Test Item", "This is a test item", new Amount(50), new Amount(5));
        Amount quantity = new Amount(2);
        item = new Item(itemIdentifier, itemInformation, quantity);
    }

    @AfterEach
    public void cleanUp() {
        item = null;
    }

    @Test
    public void getItemIdentifier() {
        String expectedItemIdentifier = "12345";
        assertEquals(expectedItemIdentifier, item.getItemIdentifier(), "Item identifier should match the expected value.");
    }

    @Test
    public void getItemInformation() {
        ItemDetails expectedItemInformation = new ItemDetails("Test Item", "This is a test item", new Amount(50), new Amount(5));
        assertEquals(expectedItemInformation.getItemName(), item.getItemInformation().getItemName(), "Item name should match the expected value.");
        assertEquals(expectedItemInformation.getItemDescription(), item.getItemInformation().getItemDescription(), "Item description should match the expected value.");
        assertEquals(expectedItemInformation.getItemPrice().getAmount(), item.getItemInformation().getItemPrice().getAmount(), "Item price should match the expected value.");
        assertEquals(expectedItemInformation.getItemTaxAmount().getAmount(), item.getItemInformation().getItemTaxAmount().getAmount(), "Item tax amount should match the expected value.");
    }

    @Test
    public void increaseQuantity() {
        Amount increaseBy = new Amount(3);
        item.increaseQuantity(increaseBy);
        Amount expectedQuantity = new Amount(5);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount(), "Quantity should increase by the specified amount.");
    }

    @Test
    public void decreaseQuantity() {
        Amount decreaseBy = new Amount(1);
        item.decreaseQuantity(decreaseBy);
        Amount expectedQuantity = new Amount(1);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount(), "Quantity should decrease by the specified amount.");
    }

    @Test
    public void getQuantity() {
        Amount expectedQuantity = new Amount(2);
        assertEquals(expectedQuantity.getAmount(), item.getQuantity().getAmount(), "Quantity should match the expected value.");
    }

    @Test
    public void setQuantity() {
        Amount newQuantity = new Amount(10);
        item.setQuantity(newQuantity);
        assertEquals(newQuantity.getAmount(), item.getQuantity().getAmount(), "Quantity should match the new value after setting.");
    }
}
