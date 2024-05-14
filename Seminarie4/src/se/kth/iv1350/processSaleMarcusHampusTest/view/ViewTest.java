package se.kth.iv1350.processSaleMarcusHampusTest.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.controller.Controller;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.InventorySystem;
import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;
import se.kth.iv1350.processSaleMarcusHampus.view.View;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ViewTest {

    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        Controller contr = new Controller(new AccountingSystem(), new InventorySystem(), new Printer());
        instanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;

        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testFakeSale() {
        instanceToTest.fakeSale();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
    }
}