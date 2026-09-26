package com.shopsmart.payment;

/**
 * PaymentGateway defines the contract used by payment providers.
 * The concrete implementations may represent different processing channels such
 * as credit card or UPI, while the business logic stays decoupled from them.
 */
public interface PaymentGateway {
    boolean executeTransaction(double amount);
}