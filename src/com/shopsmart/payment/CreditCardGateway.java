package com.shopsmart.payment;

/**
 * Simulated credit-card payment provider.
 */
public class CreditCardGateway implements PaymentGateway {
    @Override
    public boolean executeTransaction(double amount) {
        System.out.println("[Credit Card] Processing secure charge of $" + amount);
        return true;
    }
}
