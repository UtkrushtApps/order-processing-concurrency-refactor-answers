package com.company.orderprocessing;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessingDemo {
    public static void main(String[] args) {
        int initialStock = 100;
        int nOrders = 80;
        int maxOrderSize = 4;
        int threadPoolSize = 8;

        // Build orders
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= nOrders; i++) {
            int quantity = (i % maxOrderSize) + 1;
            orders.add(new Order(i, quantity, "Customer" + i));
        }

        // Sequential processing demo
        Inventory seqInventory = new Inventory(initialStock);
        OrderProcessor seqProcessor = new OrderProcessor(seqInventory);
        long seqStart = System.nanoTime();
        seqProcessor.processSequentially(orders);
        long seqEnd = System.nanoTime();
        long seqTimeMs = (seqEnd - seqStart) / 1_000_000;
        System.out.println("\nSEQUENTIAL processing finished in " + seqTimeMs + " ms, stock left: " + seqInventory.getStock());

        // Concurrent processing demo
        Inventory conInventory = new Inventory(initialStock);
        OrderProcessor conProcessor = new OrderProcessor(conInventory);
        ConcurrentOrderProcessor concurrentOrderProcessor = new ConcurrentOrderProcessor(conProcessor);
        long conStart = System.nanoTime();
        concurrentOrderProcessor.processConcurrently(orders, threadPoolSize);
        long conEnd = System.nanoTime();
        long conTimeMs = (conEnd - conStart) / 1_000_000;
        System.out.println("CONCURRENT processing finished in " + conTimeMs + " ms, stock left: " + conInventory.getStock());

        // Simple stat output
        System.out.println("\nPerformance improvement (sequential/concurrent): " + ((double)seqTimeMs/conTimeMs));
    }
}
