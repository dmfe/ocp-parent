package com.nc.ocp.exception;

import com.nc.ocp.exception.client.Client;
import com.nc.ocp.exception.data.Assertions;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));
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

    private String getDataFromDatabase() throws SQLException {
        throw new UnsupportedOperationException();
    }
}