package com.shopsmart.model;

public class ShippingAddress {
    private String street;
    private String city;

    public ShippingAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() { return street + ", " + city; }
}
