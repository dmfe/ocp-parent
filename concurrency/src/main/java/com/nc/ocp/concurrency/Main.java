package com.nc.ocp.concurrency;

import com.nc.ocp.concurrency.test.SyncTester;
import com.nc.ocp.concurrency.test.ThreadTester;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));

        new Main().run();
    }

    private void run() throws InterruptedException {
        log.info("Concurrency invokeAllTest application started...");
        new ThreadTester().run();
        new SyncTester().run();
    }
}