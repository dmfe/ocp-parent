package com.nc.ocp.func;

import com.nc.ocp.func.interfaces.InterfacesTest;
import com.nc.ocp.func.optional.OptionalTest;
import com.nc.ocp.func.streams.StreamsTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));
        new Main().start();
    }

    private void start() {
        log.info("Functional programming tests...");
        new InterfacesTest().run();
        new OptionalTest().run();
        new StreamsTest().run();
    }
}