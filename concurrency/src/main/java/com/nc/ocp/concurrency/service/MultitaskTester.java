package com.nc.ocp.concurrency.service;

import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Log4j
public class MultitaskTester {

    public void invokeAllTest() {
        ExecutorService service = null;

        List<Callable<String>> tasks = Arrays.asList(
                this::task,
                this::task,
                this::task,
                this::task,
                this::task);

        try {
            service = Executors.newSingleThreadExecutor();
            log.info("Submitting all tasks to single thread executor.");
            List<Future<String>> taskResults = service.invokeAll(tasks);
            log.info("Code point after submitting tasks to thread executor.");

            log.info("Getting tasks results:");
            for(Future<String> future : taskResults) {
                log.info(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            if (e instanceof InterruptedException )
                log.error("One of the task in the thread executor was interrupted: " + e.getLocalizedMessage(), e);
            else
                log.error("Execution exception: " + e.getLocalizedMessage(), e);
        } finally {
            if (service != null) service.shutdown();
        }
    }

    public void waitAllTasksTest() {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            for(int i = 0; i < 6; i++) {
                service.submit(this::task);
            }
        } finally {
            if (service != null) service.shutdown();
        }
        if (service != null) {
            try {
                service.awaitTermination(10, TimeUnit.SECONDS);

                if (service.isTerminated())
                    log.info("All task are finished.");
                else
                    log.info("At least one task is still running.");
            } catch (InterruptedException e) {
                log.error("Wait thread was interrupted: " + e.getLocalizedMessage(), e);
            }
        }
    }

    private String task() throws InterruptedException {
        long threadId = Thread.currentThread().getId();
        log.info("Thread " + threadId + " started.");
        Thread.sleep(2000);
        return "Result of thread " + threadId;
    }
}
