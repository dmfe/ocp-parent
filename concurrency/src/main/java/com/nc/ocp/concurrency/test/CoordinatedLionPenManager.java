package com.nc.ocp.concurrency.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoordinatedLionPenManager {

    private void removeAnimals() {
        log.info("Removing animals");
    }

    private void cleanPen() {
        log.info("Cleaning the pen");
    }

    private void addAnimals() {
        log.info("Adding animals");
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException ex) {
            log.error("Exception occured during waitingL: " + ex.getLocalizedMessage());
        }

    }
}
