package com.nc.ocp.io;

import com.nc.ocp.io.file.FileTest;
import com.nc.ocp.io.stream.StreamTest;
import com.nc.ocp.io.text.TextTest;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;

@Log4j
public class Main {

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));

        new Main().run();
    }

    private void run() {
        log.info("IO invokeAllTest application started...");
        new FileTest().run();
        new StreamTest().run();
        new TextTest().run();
    }
}
