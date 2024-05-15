package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemDetails;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemNotFoundException;
import se.kth.iv1350.processSaleMarcusHampus.integration.DatabaseConnectionException;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class InventorySystemTest {

    private InventorySystem inventorySystem;
    private Sale sale;
    private SaleDTO saleInformation;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
        sale = new Sale();
        ItemDetails milkDTO = new ItemDetails("Milk", "Dairy", new Amount(12), new Amount(2));
        ItemDetails bananaDTO = new ItemDetails("Banana", "Fruit", new Amount(5), new Amount(1));
        ItemDetails icecreamDTO = new ItemDetails("Icecream", "Frozen", new Amount(49), new Amount(6));
        ItemDetails pastaDTO = new ItemDetails("Pasta", "Dry goods", new Amount(15), new Amount(3));
        sale.addItem(new Item("32001", milkDTO, new Amount(0)), new Amount(5));
        sale.addItem(new Item("32002", bananaDTO, new Amount(0)), new Amount(3));
        sale.addItem(new Item("32003", icecreamDTO, new Amount(0)), new Amount(2));
        sale.addItem(new Item("32004", pastaDTO, new Amount(0)), new Amount(4));
        saleInformation = new SaleDTO(sale);
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        sale = null;
    }

    @Test
    public void testFetchItem() throws ItemNotFoundException, DatabaseConnectionException {
        String itemIdentifier = "32001";
        Item fetchedItem = inventorySystem.fetchItem(itemIdentifier);
        assertEquals(itemIdentifier, fetchedItem.getItemIdentifier(),
                "Fetched item's identifier should match requested.");
    }

    @Test
    public void testFetchItemNotFound() {
        String itemIdentifier = "99999";
        assertThrows(ItemNotFoundException.class, () -> {
            inventorySystem.fetchItem(itemIdentifier);
        }, "Expected ItemNotFoundException for non-existing identifier.");
    }

    @Test
    public void testFetchItemDatabaseConnectionException() {
        String itemIdentifier = "00000";
        assertThrows(DatabaseConnectionException.class, () -> {
            inventorySystem.fetchItem(itemIdentifier);
        }, "Expected DatabaseConnectionException for database connection issue.");
    }

    @Test
    public void testUpdateInventorySystem() throws ItemNotFoundException, DatabaseConnectionException {
        inventorySystem.updateInventorySystem(saleInformation);

        assertEquals(5, inventorySystem.fetchItem("32001").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(7, inventorySystem.fetchItem("32002").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(8, inventorySystem.fetchItem("32003").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
        assertEquals(6, inventorySystem.fetchItem("32004").getQuantity().getAmount(),
                "Quantity of fetched item should be updated after sale.");
    }
}
