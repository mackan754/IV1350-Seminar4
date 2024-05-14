package se.kth.iv1350.processSaleMarcusHampusTest.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.processSaleMarcusHampus.integration.AccountingSystem;
import se.kth.iv1350.processSaleMarcusHampus.model.Sale;
import se.kth.iv1350.processSaleMarcusHampus.model.SaleDTO;
import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AccountingSystemTest {

    private AccountingSystem accountingSystem;
    private Sale sale;
    private SaleDTO saleInformation;

    @Before
    public void setUp() {
        accountingSystem = new AccountingSystem();
        sale = new Sale();
        saleInformation = new SaleDTO(sale);
    }

    @After
    public void tearDown() {
        accountingSystem = null;
        sale = null;
        saleInformation = null;
    }

    @Test
    public void testGetPresenInRegister() {
        Amount initialBalance = new Amount(0);
        assertEquals(initialBalance.getAmount(), accountingSystem.getPresenInRegister().getAmount(), "Initial register balance should be zero");
    }

    @Test
    public void testGetAccountingBook() {
        ArrayList<Sale> initialAccountingBook = new ArrayList<>();
        assertEquals(initialAccountingBook, accountingSystem.getAccountingBook(), "Initial accounting book should be empty");
    }

    @Test
    public void testUpdateAccountingSystem() {
        Amount payment = new Amount(100);
        Amount initialBalance = accountingSystem.getPresenInRegister();
        accountingSystem.updateAccountingSystem(saleInformation, payment);
        assertEquals(1, accountingSystem.getAccountingBook().size(),
                "Accountingbook size should be increased by 1 after updating.");
        assertEquals(initialBalance.plus(payment).getAmount(), accountingSystem.getPresenInRegister().getAmount(),
                "Balance in register should be increased by payment amount.");
    }

}
