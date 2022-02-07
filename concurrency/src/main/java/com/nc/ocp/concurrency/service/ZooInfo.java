package com.nc.ocp.concurrency.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZooInfo {

    public void run() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();

            log.info("begin");
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                log.info("Printing zoo inventory");
            });
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                for (int i = 0; i < 3; i++) {
                    log.info("Printing record: " + i);
                }
            });
            service.execute(() -> {
                //Logger log = Logger.getLogger(this.getClass());
                log.info("Printing zoo inventory");
            });
            log.info("end");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
