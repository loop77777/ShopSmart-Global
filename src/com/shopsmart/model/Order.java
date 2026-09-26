package com.shopsmart.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a customer order. A product can appear in multiple orders, while an
 * order can contain multiple products, modeled via a Set to avoid duplicates.
 */
public class Order {
    private String orderId;
    private Set<Product> products = new HashSet<>();

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public String getOrderId() {
        return orderId;
    }
}