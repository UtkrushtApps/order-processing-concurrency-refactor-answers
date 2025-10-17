package com.company.orderprocessing;

import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private int stock;
    private final ReentrantLock lock = new ReentrantLock();

    public Inventory(int initialStock) {
        this.stock = initialStock;
    }

    /**
     * Attempts to reserve the requested quantity from inventory.
     * Returns true if successful, false if insufficient stock.
     */
    public boolean reserve(int quantity) {
        lock.lock();
        try {
            if (quantity <= stock) {
                stock -= quantity;
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public int getStock() {
        lock.lock();
        try {
            return stock;
        } finally {
            lock.unlock();
        }
    }
}
