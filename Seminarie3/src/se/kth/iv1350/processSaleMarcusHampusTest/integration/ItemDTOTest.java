package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class ItemDTOTest {

    private ItemDTO itemDTO;

    @BeforeEach
    public void setUp() {
        String itemName = "Test Item";
        String itemDescription = "This is a test item";
        Amount itemPrice = new Amount(50);
        Amount itemTaxAmount = new Amount(5);
        itemDTO = new ItemDTO(itemName, itemDescription, itemPrice, itemTaxAmount);
    }

    @AfterEach
    public void cleanUp() {
        itemDTO = null;
    }

    @Test
    public void getItemName() {
        String expectedItemName = "Test Item";
        assertEquals(expectedItemName, itemDTO.getItemName(), "Item name should match the expected value.");
    }

    @Test
    public void getItemDescription() {
        String expectedItemDescription = "This is a test item";
        assertEquals(expectedItemDescription, itemDTO.getItemDescription(), "Item description should match the expected value.");
    }

    @Test
    public void getItemPrice() {
        Amount expectedItemPrice = new Amount(50);
        assertEquals(expectedItemPrice.getAmount(), itemDTO.getItemPrice().getAmount(), "Item price should match the expected value.");
    }

    @Test
    public void getItemTaxAmount() {
        Amount expectedItemTaxAmount = new Amount(5);
        assertEquals(expectedItemTaxAmount.getAmount(), itemDTO.getItemTaxAmount().getAmount(), "Item tax amount should match the expected value.");
    }
}
