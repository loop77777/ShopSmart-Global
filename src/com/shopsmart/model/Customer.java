package com.shopsmart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer extends User {
    private ShippingAddress address;
    private List<Order> orders = new ArrayList<>();

    public Customer(String userId, String name, String email, ShippingAddress address) {
        super(userId, name, email);
        this.address = address;
    }

    public void placeOrder(Order order) { orders.add(order); }

    @Override
    public void getDashboardDetails() {
        System.out.println("Customer Profile [ID=" + getUserId() + ", Name=" + getName() + ", Orders=" + orders.size() + "]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getUserId(), customer.getUserId());
    }

    @Override
    public int hashCode() { return Objects.hash(getUserId()); }
}
