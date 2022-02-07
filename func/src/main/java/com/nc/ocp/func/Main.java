package com.nc.ocp.func;

import com.nc.ocp.func.interfaces.InterfacesTest;
import com.nc.ocp.func.optional.OptionalTest;
import com.nc.ocp.func.streams.StreamsTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        log.info("Functional programming tests...");
        new InterfacesTest().run();
        new OptionalTest().run();
        new StreamsTest().run();
    }
}
