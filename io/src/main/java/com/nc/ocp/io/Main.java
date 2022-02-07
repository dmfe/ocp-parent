package com.nc.ocp.io;

import com.nc.ocp.io.file.FileTest;
import com.nc.ocp.io.input.InputTest;
import com.nc.ocp.io.output.OutputTest;
import com.nc.ocp.io.serialization.SerdeTest;
import com.nc.ocp.io.stream.StreamTest;
import com.nc.ocp.io.text.TextTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        log.info("IO invokeAllTest application started...");
        new FileTest().run();
        new StreamTest().run();
        new TextTest().run();
        new SerdeTest().run();
        new InputTest().run();
        new OutputTest().run();
    }
}
