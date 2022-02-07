package com.nc.ocp.concurrency;

import com.nc.ocp.concurrency.forkjoin.ForkJoinSample;
import com.nc.ocp.concurrency.test.ConcurrentCollectionsTester;
import com.nc.ocp.concurrency.test.ManagingTesting;
import com.nc.ocp.concurrency.test.ParallelStreamTesting;
import com.nc.ocp.concurrency.test.SyncTester;
import com.nc.ocp.concurrency.test.ThreadTester;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }

    private void run() throws InterruptedException {
        log.info("Concurrency invokeAllTest application started...");
        new ThreadTester().run();
        new SyncTester().run();
        new ConcurrentCollectionsTester().run();
        new ParallelStreamTesting().run();
        new ManagingTesting().run();
        new ForkJoinSample().run();
    }
}
