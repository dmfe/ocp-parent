package com.nc.ocp.exception.data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assertions {

    private int num;

    public Assertions(int num) {
        this.num = num;
    }

    public void printGuestsNumber() {
        assert num > 0 : "The number of guests can less than zero!";
        log.info("Number of guest is " + num);
    }
}
