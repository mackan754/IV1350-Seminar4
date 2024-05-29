package se.kth.iv1350.processSaleMarcusHampus.view;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.DatabaseConnectionException;
import se.kth.iv1350.processSaleMarcusHampus.integration.ItemNotFoundException;
import se.kth.iv1350.processSaleMarcusHampus.model.AmountDiscountStrategy;
import se.kth.iv1350.processSaleMarcusHampus.model.CompositeDiscountStrategy;
import se.kth.iv1350.processSaleMarcusHampus.model.PercentageDiscountStrategy;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * This class represents the user interface of the application. It is responsible for
 * starting and displaying the outcomes of a simulated sale process.
 */
public class View {
    private Controller contr;

    /**
     * Constructs a View instance which will interact with the specified Controller
     * for processing sales.
     *
     * @param contr the Controller that manages the operations and flow of the sale process.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a sale by interacting with the Controller to add items, display totals,
     * and handle payments. This method is intended for demonstration purposes and prints
     * the progress of a sale to the console.
     */
    public void fakeSale() {
        contr.startNewSale();
        System.out.println("A new sale has been started.\n");
        System.out.println("Cashier enter items.\n");

        try {
            System.out.println(contr.addItem("32001", new Amount(2)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Running total: " + contr.displayTotal() + "\n");

        try {
            System.out.println(contr.addItem("32003", new Amount(4)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Running total: " + contr.displayTotal() + "\n");

        try {
            System.out.println(contr.addItem("99999", new Amount(1)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Running total: " + contr.displayTotal() + "\n");

        try {
            System.out.println(contr.addItem("00000", new Amount(1)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Running total: " + contr.displayTotal() + "\n");

        try {
            System.out.println(contr.addItem("32004", new Amount(3)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Running total: " + contr.displayTotal() + "\n");

        try {
            System.out.println(contr.addItem("32001", new Amount(2)));
        } catch (ItemNotFoundException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Running total: " + contr.displayTotal());
        System.out.println();
        String output;
        output = contr.displayTotalIncludingTax();
        System.out.println("Total including VAT: " + output);

        CompositeDiscountStrategy compositeDiscount = new CompositeDiscountStrategy();
        compositeDiscount.addStrategy(new PercentageDiscountStrategy(10));
        compositeDiscount.addStrategy(new AmountDiscountStrategy(new Amount(50)));

        contr.setDiscountStrategies(compositeDiscount);

        System.out.println("Discounts added: 10%");
        System.out.println("Discount added: 50");
        output = contr.displayFinalTotal();
        System.out.println("Final total: " + output);
        System.out.println("Cashier enter amount paid");
        output = contr.enterPayment(new Amount(350));
        System.out.println("Change to give customer: " + output);

    }
}
