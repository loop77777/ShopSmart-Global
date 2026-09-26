package com.shopsmart.promotion;

/**
 * Loyalty strategy gives a higher VIP discount to repeat customers.
 */
public class LoyaltyDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscountedPrice(double originalPrice) {
        return originalPrice * 0.80; // 20% VIP markdown
    }
}