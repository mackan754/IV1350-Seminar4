package se.kth.iv1350.processSaleMarcusHampus.startup;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.util.FileLogger;
import se.kth.iv1350.processSaleMarcusHampus.view.View;

/**
 * This is the main class for the application, responsible for initializing and starting the application.
 * It sets up necessary components and triggers the start of the sale process in the view.
 */
public class Main {

    /**
     * The main method that serves as the entry point for the application.
     * It initializes components and systems required for the application to function,
     * such as AccountingSystem, InventorySystem, and Printer, and then starts a fake sale.
     *
     * @param args the command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        AccountingSystem accountingSystem = new AccountingSystem();
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        FileLogger logger = new FileLogger();
        Controller controller = new Controller(accountingSystem, inventorySystem, printer, logger);
        View view = new View(controller);
        view.fakeSale();
    }
}
