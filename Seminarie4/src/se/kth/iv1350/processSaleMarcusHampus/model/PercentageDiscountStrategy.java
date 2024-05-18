package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private int percentage;

    public PercentageDiscountStrategy(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        int discountAmount = totalAmount.getAmount() * percentage / 100;
        return new Amount(totalAmount.getAmount() - discountAmount);
    }
}
