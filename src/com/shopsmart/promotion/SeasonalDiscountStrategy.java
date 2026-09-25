package com.shopsmart.promotion;


public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscountedPrice(double price) {
        return price * 0.90; // 10% off
    }
}
