package com.nc.ocp.nio;

import com.nc.ocp.nio.paths.PathsTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        log.info("Starting NIO Experiments...");

        new PathsTest().run();
    }
}
