package com.nc.ocp.exception.data;

public class TurkeyCage implements AutoCloseable {

    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Door does not close.");
    }
}
