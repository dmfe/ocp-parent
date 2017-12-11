package com.nc.ocp.concurrency.work;

import org.apache.log4j.Logger;

public class CheckResults {
    private static final Logger LOG = Logger.getLogger(CheckResults.class);

    private static int counter = 0;

    public void checking() throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 20000; i++) CheckResults.counter++;
        }).start();

        while (CheckResults.counter < 15000) {
            LOG.info("Not reached yet.");
            Thread.sleep(1000);
        }
        LOG.info("Reached!");
    }
}
