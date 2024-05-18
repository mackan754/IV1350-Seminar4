package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

/**
 * NoDiscountStrategy provides no discount.
 */
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        return totalAmount;
    }
}
