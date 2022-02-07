package com.nc.ocp.concurrency.test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagingTesting {

    private static final int WORKERS_COUNT = 4;

    public void run() {
        simpleLionPenManagerTest();
        coordinatedLionPenManagerTest();
    }

    private void simpleLionPenManagerTest() {
        ExecutorService executorService = null;

        try {
            executorService = Executors.newFixedThreadPool(WORKERS_COUNT);

            LionPenManager lionPenManager = new LionPenManager();

            for (int i = 0; i < WORKERS_COUNT; i++) {
                executorService.submit(lionPenManager::performTask);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private void coordinatedLionPenManagerTest() {
        ExecutorService executorService = null;

        try {
            executorService = Executors.newFixedThreadPool(WORKERS_COUNT);

            CoordinatedLionPenManager lionPenManager = new CoordinatedLionPenManager();

            CyclicBarrier c1 = new CyclicBarrier(WORKERS_COUNT, () -> log.info("*** Animals removed!"));
            CyclicBarrier c2 = new CyclicBarrier(WORKERS_COUNT, () -> log.info("*** Pen cleaned!"));

            for (int i = 0; i < WORKERS_COUNT; i++) {
                executorService.submit(() -> lionPenManager.performTask(c1, c2));
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
