package com.nc.ocp.concurrency.test;

import lombok.extern.log4j.Log4j;

@Log4j
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

    public void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }
}
