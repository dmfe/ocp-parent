package com.nc.ocp.exception;

import com.nc.ocp.exception.client.Client;
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
        new Client().runClientCode();
    }

    private String getDataFromDatabase() throws SQLException {
        throw new UnsupportedOperationException();
    }
}