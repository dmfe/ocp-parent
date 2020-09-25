package com.nc.ocp.io.input;

import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class InputTest {

    public void run() {
        oldWayInputTest();
        moderInputTest();
        inputPasswordTest();
    }

    private void oldWayInputTest() {
        try {
            log.info("You entered the following: " + Input.oldWayInput());
        } catch (IOException ex) {
            log.error("Error while reading user input: " + ex.getLocalizedMessage(), ex);
        }
    }

    private void moderInputTest() {
        log.info("You entered the following: " + Input.modernInput());
    }

    private void inputPasswordTest() {
        Input.passwordInput();
    }
}
