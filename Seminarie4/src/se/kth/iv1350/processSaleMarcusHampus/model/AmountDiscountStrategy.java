package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * A discount strategy that applies a fixed discount amount to the total sale amount.
 */
public class AmountDiscountStrategy implements DiscountStrategy {
    private Amount discountAmount;

    /**
     * Creates an instance of AmountDiscountStrategy with a specified discount amount.
     * 
     * @param discountAmount The fixed amount to be discounted from the total sale amount.
     */
    public AmountDiscountStrategy(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Applies the fixed discount amount to the given total amount.
     * 
     * @param totalAmount The total amount before applying the discount.
     * @return The new total amount after applying the fixed discount. 
     *         If the discount amount exceeds the total amount, the new total will be zero.
     */
    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        int newAmount = totalAmount.getAmount() - discountAmount.getAmount();
        if (newAmount < 0) {
            newAmount = 0;
        }
        return new Amount(newAmount);
    }
}
