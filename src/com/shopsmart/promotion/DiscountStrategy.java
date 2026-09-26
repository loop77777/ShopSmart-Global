package com.shopsmart.promotion;

/**
 * Strategy interface for discount behaviors.
 * Different strategies can calculate final discounted pricing without changing
 * the checkout flow that uses them.
 */
public interface DiscountStrategy {
    double calculateDiscountedPrice(double originalPrice);
}
