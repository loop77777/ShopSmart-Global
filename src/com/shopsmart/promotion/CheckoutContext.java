package com.shopsmart.promotion;


public class CheckoutContext {
    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    public double applyStrategy(double price) {
        if (strategy == null) return price;
        return strategy.calculateDiscountedPrice(price);
    }
}