package com.nc.ocp.exception.data;

import org.apache.log4j.Logger;

public class Turkey implements AutoCloseable {
    private static final Logger log = Logger.getLogger(Turkey.class);

    private String name;

    public Turkey(String tName) {
        name = tName;
    }

    public String getName() {
        return name;
    }

    @Override
    public void close() throws Exception {
        log.info("Turkey " + name + " closed.");
    }
}
