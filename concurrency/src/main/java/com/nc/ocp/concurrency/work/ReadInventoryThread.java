package com.nc.ocp.concurrency.work;

import org.apache.log4j.Logger;

public class ReadInventoryThread extends Thread {
    private static final Logger LOG = Logger.getLogger(ReadInventoryThread.class);

    @Override
    public void run() {
        LOG.info("Printing zoo inventory...");
    }
}
