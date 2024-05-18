package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;

public class AmountDiscountStrategy implements DiscountStrategy {
    private Amount discountAmount;

    public AmountDiscountStrategy(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        int newAmount = totalAmount.getAmount() - discountAmount.getAmount();
        if (newAmount < 0) {
            newAmount = 0;
        }
        return new Amount(newAmount);
    }
}
