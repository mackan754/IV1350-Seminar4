package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import java.util.ArrayList;

public class CompositeDiscountStrategy implements DiscountStrategy {
    private ArrayList<DiscountStrategy> strategies;

    public CompositeDiscountStrategy() {
        this.strategies = new ArrayList<>();
    }

    public void addStrategy(DiscountStrategy strategy) {
        strategies.add(strategy);
    }

    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        Amount discountedAmount = totalAmount;
        for (DiscountStrategy strategy : strategies) {
            discountedAmount = strategy.calculateDiscount(discountedAmount);
        }
        return discountedAmount;
    }
}
