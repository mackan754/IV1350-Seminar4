package se.kth.iv1350.processSaleMarcusHampusTest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AmountTest {

    @Test
    public void testGetAmount() {
        int amountValue = 10;
        Amount amount = new Amount(amountValue);
        assertEquals(amountValue, amount.getAmount(), "Failed to get the correct amount");
    }

    @Test
    public void testMinus() {
        Amount amount1 = new Amount(10);
        Amount amount2 = new Amount(3);
        Amount result = amount1.minus(amount2);
        assertEquals(7, result.getAmount(), "Failed to subtract correctly");

        amount1 = new Amount(-10);
        amount2 = new Amount(-3);
        result = amount1.minus(amount2);
        assertEquals(-7, result.getAmount(), "Failed to subtract negative amounts correctly");
    }

    @Test
    public void testPlus() {
        Amount amount1 = new Amount(5);
        Amount amount2 = new Amount(3);
        Amount result = amount1.plus(amount2);
        assertEquals(8, result.getAmount(), "Failed to add correctly");

        amount1 = new Amount(-5);
        amount2 = new Amount(-3);
        result = amount1.plus(amount2);
        assertEquals(-8, result.getAmount(), "Failed to add negative amounts correctly");
    }

    @Test
    public void testMultiply() {
        Amount amount1 = new Amount(4);
        Amount amount2 = new Amount(3);
        Amount result = amount1.multiply(amount2);
        assertEquals(12, result.getAmount(), "Failed to multiply correctly");

        amount1 = new Amount(-4);
        amount2 = new Amount(3);
        result = amount1.multiply(amount2);
        assertEquals(-12, result.getAmount(), "Failed to multiply negative amounts correctly");
    }
}
