package com.company.orderprocessing;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcurrentOrderProcessor {
    private final OrderProcessor orderProcessor;
    private final Logger logger = Logger.getLogger(ConcurrentOrderProcessor.class.getName());

    public ConcurrentOrderProcessor(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    /**
     * Processes orders in parallel with a configurable thread pool.
     * Each order is processed in a separate Runnable, with error handling.
     */
    public void processConcurrently(List<Order> orders, int threadPoolSize) {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        for (Order order : orders) {
            executor.submit(() -> {
                try {
                    orderProcessor.process(order);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Order submission failed: " + order, e);
                }
            });
        }
        executor.shutdown();
        try {
            // Wait up to 2 minutes for all orders to finish
            boolean ok = executor.awaitTermination(2, TimeUnit.MINUTES);
            if (!ok) {
                logger.warning("Timeout occurred before all orders finished.");
            }
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Thread interrupted while waiting for completion.", e);
            Thread.currentThread().interrupt();
        }
    }
}
