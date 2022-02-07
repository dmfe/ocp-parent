package com.nc.ocp.concurrency.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LionPenManager {

    private void removeAnimals() {
        log.info("Removing animals");
    }

    private void cleanPen() {
        log.info("Cleaning the pen");
    }

    private void addAnimals() {
        log.info("Adding animals");
    }

    void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }
}
