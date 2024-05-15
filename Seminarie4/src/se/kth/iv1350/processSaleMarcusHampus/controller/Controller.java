package se.kth.iv1350.processSaleMarcusHampus.controller;

import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.DatabaseConnectionException;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Item;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemNotFoundException;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import se.kth.iv1350.processSaleMarcusHampus.util.FileLogger;
import se.kth.iv1350.processSaleMarcusHampus.util.Logger;

/**
 * The Controller class acts as the central part of the application,
 * coordinating interactions
 * between the user interface and the integration/model layers.
 */
public class Controller {

    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;
    private Logger logger;

    /**
     * Initializes a new Controller with necessary external systems for accounting,
     * inventory management, and printing.
     * 
     * @param accountingSystem the accounting system to be used for financial
     *                         transactions
     * @param inventorySystem  the inventory system for item data retrieval and
     *                         stock updates
     * @param printer          the printer used for printing receipts
     */
    public Controller(AccountingSystem accountingSystem, InventorySystem inventorySystem, Printer printer, FileLogger logger) {
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
        this.printer = printer;
        this.logger = logger;
    }

    /**
     * Begins a new sale, resetting the current Sale object to ensure a clean state.
     */
    public void startNewSale() {
        this.sale = new Sale();
    }

    /**
     * Adds an item to the current sale using an identifier. The quantity of the
     * item is also specified.
     * 
     * @param itemIdentifier a unique string identifier for the item to be added
     * @param quantity       the quantity of the item, encapsulated in an Amount
     *                       object
     * @return a string summarizing the added item's details
     */
    public String addItem(String itemIdentifier, Amount quantity) {
        try {
            Item itemToBeAdded = inventorySystem.fetchItem(itemIdentifier);
            sale.addItem(itemToBeAdded, quantity);
            String itemDetails = itemToBeAdded.generateItemDetails();
            return itemDetails;
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: " + itemIdentifier, e);
            return e.getMessage();
        } catch (DatabaseConnectionException e) {
            logger.error("Failed to connect to database.", e);
            return e.getMessage();
        }
    }

    /**
     * Provides the total cost of items in the current sale, excluding tax.
     *
     * @return the total amount as a string
     */
    public String displayTotal() {
        return sale.getTotal().toString();
    }

    /**
     * Provides the total cost of items in the current sale, including tax.
     *
     * @return the total amount including tax as a string
     */
    public String displayTotalIncludingTax() {
        return sale.getTotalIncludingTax().toString();
    }

    /**
     * Completes the sale by processing the payment, calculating change, printing a
     * receipt,
     * and updating the inventory and accounting systems.
     *
     * @param payment the amount paid by the customer
     * @return string representing the change to be given to the customer
     */
    public String enterPayment(Amount payment) {
        Amount change = sale.completeSale(payment);

        SaleDTO saleInformation = new SaleDTO(sale);
        Receipt receipt = new Receipt(saleInformation);
        printer.print(receipt);

        inventorySystem.updateInventorySystem(saleInformation);
        accountingSystem.updateAccountingSystem(saleInformation, payment);

        return change.toString();
    }
}
