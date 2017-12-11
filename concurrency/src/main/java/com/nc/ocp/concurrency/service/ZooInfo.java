package com.nc.ocp.concurrency.service;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZooInfo {
    private static final Logger LOG = Logger.getLogger(ZooInfo.class);

    public void run() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();

            LOG.info("begin");
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                LOG.info("Printing zoo inventory");
            });
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                for(int i = 0; i < 3; i++) {
                    LOG.info("Printing record: " + i);
                }
            });
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                LOG.info("Printing zoo inventory");
            });
            LOG.info("end");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}