package se.kth.iv1350.processSaleMarcusHampusTest.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.model.Receipt;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;

public class ReceiptTest {
    private Sale sale;
    private SaleDTO saleInformation;
    private Receipt receipt;

    @Before
    public void setUp() {
        sale = new Sale();
        saleInformation = new SaleDTO(sale);
        receipt = new Receipt(saleInformation);
    }

    @Test
    public void testToStringWithEmptyReceipt() {
        
        String expected ="\n" +
        "-----RECEIPT-----\n" +
        saleInformation.getFormattedSaleTime().toString() + "\n" +
        saleInformation.toString() + "\n" +
        "-------END-------";

        assertEquals(expected, receipt.toString(), "Receipt string should match the format for an empty receipt.");
    }
}