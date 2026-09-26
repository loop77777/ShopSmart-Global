package com.shopsmart.model;

/**
 * A customer shipping location. This models the one-to-one shipping association.
 */
public class ShippingAddress {
    private String street;
    private String city;

    public ShippingAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}
