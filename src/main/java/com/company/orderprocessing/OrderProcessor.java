package com.company.orderprocessing;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderProcessor {
    public final Inventory inventory;
    private final Logger logger = Logger.getLogger(OrderProcessor.class.getName());

    public OrderProcessor(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Processes a single order: reserves stock if available; logs failure otherwise.
     * Throws if an unexpected error occurs.
     */
    public void process(Order order) {
        try {
            if (inventory.reserve(order.getQuantity())) {
                logger.info("Order Processed: " + order + ", Stock Left: " + inventory.getStock());
            } else {
                logger.warning("Order Failed (not enough stock): " + order);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing " + order, e);
        }
    }

    /**
     * Sequential (legacy) processing for demonstration.
     */
    public void processSequentially(List<Order> orders) {
        for (Order o : orders) {
            process(o);
        }
    }
}
