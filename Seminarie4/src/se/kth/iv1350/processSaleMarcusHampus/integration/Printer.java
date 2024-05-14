package se.kth.iv1350.processSaleMarcusHampus.integration;

import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;

/**
 * This class is responsible for handling the printing operations in the application.
 * It interacts with the Receipt class to receive and print the receipt details to the standard output.
 */
public class Printer {

    /**
     * Constructs a new Printer instance.
     */
    public Printer() {
    }

    /**
     * Prints the formatted receipt details to the standard output.
     * 
     * @param receipt the Receipt object that contains the details to be printed
     */
    public void print(Receipt receipt) {
        System.out.println(receipt.toString());
    }

    /**
     * Prints a string to the standard output.
     * 
     * @param string the string to be printed
     */
    public void printString(String string) {
        System.out.println(string);
    }
}
