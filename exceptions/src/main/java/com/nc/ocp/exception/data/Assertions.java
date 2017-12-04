package com.nc.ocp.exception.data;

import org.apache.log4j.Logger;

public class Assertions {
    private static final Logger log = Logger.getLogger(Assertions.class);

    private int num;

    public Assertions(int num) {
        this.num = num;
    }

    public void printGuestsNumber() {
        assert num > 0 : "The number of guests can less than zero!";
        log.info("Number of guest is " + num);
    }
}