package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {

    @Test
    public void testPrint() {
        
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Printer printer = new Printer();

        String testString = "This is a test string.";

        printer.printString(testString);

        String printedOutput = outputStreamCaptor.toString().trim();

        assertEquals(testString, printedOutput);
    }
}
