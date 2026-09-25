package com.shopsmart.repository;

import com.shopsmart.model.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderRepository {
    private Map<String, Order> orderMap = new HashMap<>();
    private TreeSet<String> sortedOrderIds = new TreeSet<>();

    public void save(Order order) {
        if (order != null) {
            orderMap.put(order.getOrderId(), order);
            sortedOrderIds.add(order.getOrderId());
        }
    }

    public Order findById(String id) { return orderMap.get(id); }
    public SortedSet<String> getSortedOrderIds() { return sortedOrderIds; }
}
