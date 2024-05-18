package se.kth.iv1350.processSaleMarcusHampus.model;

import se.kth.iv1350.processSaleMarcusHampus.util.Amount;
import java.util.ArrayList;

/**
 * A discount strategy that applies multiple discount strategies in sequence.
 * The final discount is the result of applying all the individual discount strategies.
 */
public class CompositeDiscountStrategy implements DiscountStrategy {
    private ArrayList<DiscountStrategy> strategies;

    /**
     * Creates an instance of CompositeDiscountStrategy with an empty list of discount strategies.
     */
    public CompositeDiscountStrategy() {
        this.strategies = new ArrayList<>();
    }

    /**
     * Adds a discount strategy to the composite strategy.
     * 
     * @param strategy The discount strategy to be added.
     */
    public void addStrategy(DiscountStrategy strategy) {
        strategies.add(strategy);
    }

    /**
     * Applies all the discount strategies in sequence to the given total amount.
     * 
     * @param totalAmount The total amount before applying the discounts.
     * @return The new total amount after applying all the discount strategies.
     */
    @Override
    public Amount calculateDiscount(Amount totalAmount) {
        Amount discountedAmount = totalAmount;
        for (DiscountStrategy strategy : strategies) {
            discountedAmount = strategy.calculateDiscount(discountedAmount);
        }
        return discountedAmount;
    }
}
