package com.nc.ocp.concurrency.work;

import org.apache.log4j.Logger;

public class PrintData implements Runnable {
    private static final Logger LOG = Logger.getLogger(PrintData.class);

    @Override
    public void run() {
        for (int i = 0; i < 3; i++)
            LOG.info("Printing record: " + i);
    }
}
