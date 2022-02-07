package com.nc.ocp.exception.exceptions;

public class CannotSwimException extends Exception {

    public CannotSwimException() {
        super();
    }

    public CannotSwimException(String message) {
        super(message);
    }

    public CannotSwimException(Throwable cause) {
        super(cause);
    }
}
