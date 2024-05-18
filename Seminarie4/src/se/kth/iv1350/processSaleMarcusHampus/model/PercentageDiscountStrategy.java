package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * A discount strategy that applies a percentage-based discount to the total sale amount.
 */
public class PercentageDiscountStrategy implements DiscountStrategy {
    private int percentage;

    /**
     * Creates an instance of PercentageDiscountStrategy with a specified discount percentage.
     * 
     * @param percentage The percentage to be discounted from the total sale amount.
     */
    public PercentageDiscountStrategy(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Applies the percentage discount to the given total amount.
     * 
     * @param totalAmount The total amount before applying the discount.
     * @return The new total amount after applying the percentage discount.
     */
    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        int discountAmount = totalAmount.getAmount() * percentage / 100;
        return new Amount(totalAmount.getAmount() - discountAmount);
    }
}
