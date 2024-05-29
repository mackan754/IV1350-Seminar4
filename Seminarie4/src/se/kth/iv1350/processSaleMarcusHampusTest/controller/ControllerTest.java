package se.kth.iv1350.processSaleMarcusHampusTest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.DatabaseConnectionException;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemNotFoundException;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import se.kth.iv1350.processSaleMarcusHampus.util.FileLogger;
import se.kth.iv1350.processSaleMarcusHampus.util.TotalRevenueObserver;

public class ControllerTest {

    private Controller controller;
    private InventorySystem inventorySystem;
    private Printer printer;
    private FileLogger logger;
    private ArrayList<TotalRevenueObserver> revenueObservers;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
        printer = new Printer();
        logger = new FileLogger();
        revenueObservers = new ArrayList<>();
        controller = new Controller(inventorySystem, printer, logger, revenueObservers);
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        printer = null;
        controller = null;
        logger = null;
        revenueObservers = null;
    }

    @Test
    public void testStartNewSale() {
        controller.startNewSale();
        assertEquals("0", controller.displayTotal(), "Sale did not start correctly.");
    }

    @Test
    public void testAddItem() {
        controller.startNewSale();
        Amount quantity = new Amount(2);
        try {
            controller.addItem("32001", quantity);
            String expectedTotal = "24";
            assertEquals(expectedTotal, controller.displayTotal(),
                    "Adding an existing inventory item did not update the sale correctly.");
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            fail("Exception should not be thrown for a valid item and database connection.");
        }
    }

    @Test
    public void testDisplayTotal() {
        controller.startNewSale();
        try {
            controller.addItem("32001", new Amount(2));
            controller.addItem("32002", new Amount(3));
            String expectedTotal = "39";
            assertEquals(expectedTotal, controller.displayTotal(),
                    "Displayed total does not match the expected total without considering tax.");
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            fail("Exception should not be thrown for valid items and database connection.");
        }
    }

    @Test
    public void testDisplayTotalIncludingTax() {
        controller.startNewSale();
        try {
            controller.addItem("32001", new Amount(2));
            controller.addItem("32002", new Amount(3));
            String expectedTotal = "46";
            assertEquals(expectedTotal, controller.displayTotalIncludingTax(),
                    "Displayed total including tax does not match the expected total.");
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            fail("Exception should not be thrown for valid items and database connection.");
        }
    }

    @Test
    public void testEnterPayment() {
        controller.startNewSale();
        try {
            controller.addItem("32001", new Amount(2));
            Amount payment = new Amount(100);
            Amount expectedChange = payment.minus(new Amount(28));
            assertEquals(expectedChange.toString(), controller.enterPayment(payment),
                    "Returned change does not match the expected change after payment.");
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            fail("Exception should not be thrown for valid items and database connection.");
        }
    }

    @Test
    public void testItemNotFoundException() {
        controller.startNewSale();
        Amount quantity = new Amount(1);
        try {
            controller.addItem("invalid", quantity);
            fail("Exception should be thrown for an invalid item identifier.");
        } catch (ItemNotFoundException e) {
            assertEquals("Item with identifier invalid was not found.", e.getMessage(),
                    "The error message for an item not found does not match.");
        } catch (DatabaseConnectionException e) {
            fail("DatabaseConnectionException should not be thrown for an invalid item identifier.");
        }
    }

    @Test
    public void testDatabaseConnectionException() {
        controller.startNewSale();
        Amount quantity = new Amount(1);
        try {
            controller.addItem("00000", quantity);
            fail("Exception should be thrown for a database connection error.");
        } catch (ItemNotFoundException e) {
            fail("ItemNotFoundException should not be thrown for a database connection error.");
        } catch (DatabaseConnectionException e) {
            assertEquals("Database connection failure: Failed to connect to database.", e.getMessage(),
                    "The error message for a database connection exception does not match.");
        }
    }

}
