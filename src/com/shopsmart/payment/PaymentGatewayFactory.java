package com.shopsmart.payment;


public class PaymentGatewayFactory {
    public static PaymentGateway getGateway(String type) {
        if (type == null) return null;
        if (type.equalsIgnoreCase("CARD")) return new CreditCardGateway();
        if (type.equalsIgnoreCase("UPI")) return new UpiGateway();
        throw new IllegalArgumentException("Unknown gateway type: " + type);
    }
}
