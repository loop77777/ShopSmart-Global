package com.shopsmart.promotion;

/**
 * Seasonal discount strategy gives a 10% reduction during special offers.
 */
public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscountedPrice(double price) {
        return price * 0.90; // 10% off
    }
}
