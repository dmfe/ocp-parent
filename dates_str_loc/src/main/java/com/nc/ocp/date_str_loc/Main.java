package com.nc.ocp.date_str_loc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));
        new Main().start();
    }

    private void start() {
        log.info("Date, Strings, Localization tests...");
    }
}