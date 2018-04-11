package com.nc.ocp.concurrency.service;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Log4j
public class SchedulingTester {

    public void run() {

        Runnable t1 = () -> log.info("Hello zoo");
        Callable<String> t2 = () -> "Monkey";

        ScheduledExecutorService service = null;
        try {
            service = Executors.newSingleThreadScheduledExecutor();

            Future<?> res1 = service.schedule(t1, 3, TimeUnit.SECONDS);
            Future<String> res2 = service.schedule(t2, 5, TimeUnit.SECONDS);

            log.info(res2.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred while waiting task execution result: " + e.getLocalizedMessage(), e);
        } finally {
            if (service != null) service.shutdown();
        }

        periodAtFixedRateTest();
        periodAtFixedDelayTest();
    }

    private void periodAtFixedRateTest() {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable periodTask = () -> log.info("Hi from fixed rate period.");
        Runnable shutdownTask = () -> service.shutdown();

        service.scheduleAtFixedRate(periodTask, 2, 5, TimeUnit.SECONDS);
        service.schedule(shutdownTask, 20, TimeUnit.SECONDS);
    }

    private void periodAtFixedDelayTest() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable periodTask = () -> log.info("Hi from fixed delay.");
        Runnable shutdownTask = () -> service.shutdown();

        service.scheduleWithFixedDelay(periodTask, 2, 5, TimeUnit.SECONDS);
        service.schedule(shutdownTask, 20, TimeUnit.SECONDS);
    }
}
