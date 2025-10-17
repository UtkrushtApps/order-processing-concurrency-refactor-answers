# Solution Steps

1. Define the Order model to represent customer orders with orderId, quantity, and customer details.

2. Implement the Inventory class to manage stock counts; ensure that reservation/sale access to the stock is synchronized using a lock to avoid overbooking in concurrent situations.

3. Implement OrderProcessor to process single orders: reserve stock if available; log failed orders using Java's Logger. Provide sequential processing for legacy demo.

4. Implement ConcurrentOrderProcessor to process orders in parallel using a configurable ExecutorService. Each order is processed in a Runnable with robust error handling so failures are logged without stopping the process.

5. Write the OrderProcessingDemo class to demonstrate and benchmark both approaches: Create a batch of orders, process them sequentially, then process the same set concurrently, recording and printing the elapsed time for each. Output the stock remaining and demonstrate that overbooking does not occur.

6. Test the demo to see that concurrent processing is significantly faster for large batches, failed orders are logged, and inventory is never overbooked.

