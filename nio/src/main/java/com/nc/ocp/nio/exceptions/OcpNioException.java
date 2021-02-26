package com.nc.ocp.nio.exceptions;

/**
 * OcpNioException
 */
public class OcpNioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OcpNioException(String msg, Throwable cr) {
        super(msg, cr);
    }
}
