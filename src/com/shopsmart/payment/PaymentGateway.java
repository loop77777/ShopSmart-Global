package com.shopsmart.payment;

public interface PaymentGateway {
    boolean executeTransaction(double amount);
}