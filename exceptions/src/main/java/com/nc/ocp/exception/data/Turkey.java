package com.nc.ocp.exception.data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Turkey implements AutoCloseable {

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
