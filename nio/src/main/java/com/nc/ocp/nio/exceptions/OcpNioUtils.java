package com.nc.ocp.nio.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class OcpNioUtils {

    private OcpNioUtils() {
    }

    public static OcpNioException produceOcpNioException(String msg, Exception e) {
        String message = msg + e.getLocalizedMessage();
        log.error(msg, e);
        return new OcpNioException(message, e);
    }
}
