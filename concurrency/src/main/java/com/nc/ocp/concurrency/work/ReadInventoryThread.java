package com.nc.ocp.concurrency.work;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadInventoryThread extends Thread {

    @Override
    public void run() {
        log.info("Printing zoo inventory...");
    }
}
