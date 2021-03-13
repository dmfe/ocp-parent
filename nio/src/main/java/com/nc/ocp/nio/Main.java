package com.nc.ocp.nio;

import com.nc.ocp.nio.paths.PathsTest;

import org.apache.log4j.PropertyConfigurator;

import lombok.extern.log4j.Log4j;

@Log4j
public class Main {

    public static void main(String[] args) {

        PropertyConfigurator.configure(Main.class.getClassLoader()
                .getResourceAsStream("log4j.properties"));

        new Main().run();
    }

    private void run() {
        log.info("Starting NIO Experiments...");

        new PathsTest().run();
    }
}
