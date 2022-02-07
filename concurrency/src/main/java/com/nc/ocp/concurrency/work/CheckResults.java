package com.nc.ocp.concurrency.work;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckResults {

    private static int counter = 0;

    public void checking() throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 20000; i++) CheckResults.counter++;
        }).start();

        while (CheckResults.counter < 15000) {
            log.info("Not reached yet.");
            Thread.sleep(1000);
        }
        log.info("Reached!");
    }
}
