package com.shopsmart.promotion;

/**
 * Context class that delegates price calculation to the currently selected
 * discount strategy. This is the Strategy Pattern implementation.
 */
public class CheckoutContext {
    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyStrategy(double price) {
        if (strategy == null) {
            return price;
        }
        return strategy.calculateDiscountedPrice(price);
    }
}