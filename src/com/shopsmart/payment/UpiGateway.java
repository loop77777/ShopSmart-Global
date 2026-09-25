package com.shopsmart.payment;


public class UpiGateway implements PaymentGateway {
    @Override
    public boolean executeTransaction(double amount) {
        System.out.println("Processing instant UPI payment of $" + amount);
        return true;
    }
}