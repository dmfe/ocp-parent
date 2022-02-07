package com.nc.ocp.concurrency.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifiedCheckResults {
    private static int counter = 0;

    public void run() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<String> result = service.submit(() -> {
                for (int i = 0; i < 11000; i++) {
                    ModifiedCheckResults.counter++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("Counter thread was interrupted: ", e);
                        break;
                    }
                }
                return "WAY POINT REACHED!";
            });

            while (!result.isDone()) {
                try {
                    log.info("Not reached...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("Waiting thread was interrupted: ", e);
                }
            }
            log.info(result.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occur while waiting thread result: ", e);
        } finally {
            if (service != null) service.shutdown();
        }
    }

    public void alternativeRun() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<String> result = service.submit(() -> {
                int i = 0;
                while (i < 1000) {
                    ModifiedCheckResults.counter++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("Counter thread was interrupted: ", e);
                        break;
                    }
                    i++;
                }
                return "ALTERNATIVE WAY POINT REACHED!";
            });
            log.info("Task submitted.");
            log.info(result.get(10, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            log.error("Timeout: ", e);
        } catch (ExecutionException | InterruptedException e) {
            log.error("Exception occur while waiting thread result: ", e);
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
