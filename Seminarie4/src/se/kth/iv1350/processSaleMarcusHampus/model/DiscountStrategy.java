package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * DiscountStrategy interface declares the method for calculating the discount.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount for the given amount.
     *
     * @param totalAmount the total amount before discount
     * @return the discounted amount
     */
    Amount calculateDiscount(Amount totalAmount);
}
