package com.nc.ocp.concurrency.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrentCollectionsTester {

    public void run() {
        consistencyTest();
        concurrentClassesTest();
        blockingQueueTest();
        copyOnWriteTest();
        synchronizedCollectionsTest();
    }

    private void consistencyTest() {
        Map<String, Object> foodData = new ConcurrentHashMap<>();//new HashMap<>();
        foodData.put("Penguin", 1);
        foodData.put("Flamingo", 2);

        for (String key : foodData.keySet()) {
            foodData.remove(key);
            log.info(key + " removed.");
        }

        log.info("foodData: " + foodData);
    }

    private void concurrentClassesTest() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("zebra", 52);
        map.put("elephant", 63);
        log.info("{}", map.get("elephant"));

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        log.info("{}", queue.peek());
        log.info("{}", queue.poll());
        log.info("queue: " + queue);

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(20);
        deque.push(4);
        log.info("{}", deque.peek());
        log.info("{}", deque.poll());
        log.info("deque: " + deque);
    }

    private void blockingQueueTest() {
        try {
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
            blockingQueue.offer(39);
            blockingQueue.offer(3, 4, TimeUnit.SECONDS);

            log.info("blocking queue poll: " + blockingQueue.poll());
            log.info("blocking queue poll with waiting: " + blockingQueue.poll(10, TimeUnit.MILLISECONDS));

            // Blocking Deque testing
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
            blockingDeque.offer(20);
            blockingDeque.offerFirst(5, 1, TimeUnit.MINUTES);
            blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
            //blockingDeque.offer(3, 4, TimeUnit.SECONDS);

            log.info("blocking deque poll: " + blockingDeque.poll());
            log.info("blocking deque poll with waiting: " + blockingDeque.poll(950, TimeUnit.MILLISECONDS));
            log.info("blocking deque poll first with waiting: " + blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
            log.info("blocking deque poll last with waiting: " + blockingDeque.pollLast(5, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            log.error("Queue waiting was interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private void copyOnWriteTest() {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list) {
            log.info("List item: " + item);
            list.add(9);
        }
        log.info("Result list: " + list);
    }

    private void synchronizedCollectionsTest() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(3, 4, 5)));
        // Access on any iterators are not synchronized. Therefore, it's imperative that you use a
        // synchronization block if you need to iterate over any of synchronized collection.
        synchronized (this) {
            for (Integer item : list) {
                log.info("Synchronized collection test: " + item);
            }
        }
    }
}
