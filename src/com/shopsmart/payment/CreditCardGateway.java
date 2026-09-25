package com.shopsmart.payment;


public class CreditCardGateway implements PaymentGateway {
    @Override
    public boolean executeTransaction(double amount) {
        System.out.println("Processing secure Credit Card charge of $" + amount);
        return true;
    }
}
