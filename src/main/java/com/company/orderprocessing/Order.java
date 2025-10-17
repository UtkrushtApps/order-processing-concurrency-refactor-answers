package com.company.orderprocessing;

public class Order {
    private final int orderId;
    private final int quantity;
    private final String customer;

    public Order(int orderId, int quantity, String customer) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return String.format("Order{id=%d, qty=%d, customer='%s'}", orderId, quantity, customer);
    }
}
