package com.nc.ocp.concurrency.test;

import com.nc.ocp.concurrency.service.ModifiedCheckResults;
import com.nc.ocp.concurrency.service.MultitaskTester;
import com.nc.ocp.concurrency.service.SchedulingTester;
import com.nc.ocp.concurrency.service.ZooInfo;
import com.nc.ocp.concurrency.work.CheckResults;
import com.nc.ocp.concurrency.work.PrintData;
import com.nc.ocp.concurrency.work.ReadInventoryThread;

public class ThreadTester {

    public void run() throws InterruptedException {
        runRunnable();
        runThread();
        pollingTest();
        execServiceTest();
        scheduleTest();
    }

    private void runRunnable() {
        new Thread(new PrintData()).start();
    }

    private void runThread() {
        new ReadInventoryThread().start();
    }

    private void pollingTest() throws InterruptedException {
        new CheckResults().checking();
    }

    private void execServiceTest() {
        new ZooInfo().run();
        //new ModifiedCheckResults().run();
        new ModifiedCheckResults().alternativeRun();
        MultitaskTester multitaskTester = new MultitaskTester();
        multitaskTester.invokeAllTest();
        multitaskTester.waitAllTasksTest();
    }

    private void scheduleTest() {
        new SchedulingTester().run();
    }
}
