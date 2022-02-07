package com.nc.ocp.exception;

import com.nc.ocp.exception.client.Client;
import com.nc.ocp.exception.data.Assertions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        log.info("Exceptions test started...");
        Client client = new Client();
        client.runClientCode();
        client.parseDateFromFile();
        client.copyFileContent("in.txt", "out.txt");
        client.turkeyAutoCloseableTest();
        client.supressionTest();

        // Assertions test.
        Assertions ass = new Assertions(10);
        ass.printGuestsNumber();
    }
}
