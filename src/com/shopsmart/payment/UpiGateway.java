package com.shopsmart.payment;

/**
 * Simulated UPI payment provider.
 */
public class UpiGateway implements PaymentGateway {
    @Override
    public boolean executeTransaction(double amount) {
        System.out.println("[UPI] Processing instant payment of $" + amount);
        return true;
    }
}