package com.shopsmart.payment;

/**
 * Factory pattern implementation for runtime selection of payment providers.
 */
public class PaymentGatewayFactory {
    public static PaymentGateway getGateway(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Payment type cannot be null");
        }
        if (type.equalsIgnoreCase("CARD")) {
            return new CreditCardGateway();
        }
        if (type.equalsIgnoreCase("UPI")) {
            return new UpiGateway();
        }
        throw new IllegalArgumentException("Unknown gateway type: " + type);
    }
}
