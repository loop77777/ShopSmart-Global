package com.shopsmart.model;


import java.util.HashSet;
import java.util.Set;

public class Order {
    private String orderId;
    private Set<Product> products = new HashSet<>();

    public Order(String orderId) { this.orderId = orderId; }

    public void addProduct(Product p) { products.add(p); }
    public Set<Product> getProducts() { return products; }
    public String getOrderId() { return orderId; }
}