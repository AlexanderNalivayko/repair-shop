package com.nalivayko.pool.exceptions;

public class InternalAppException extends RuntimeException {

    public InternalAppException(String message) {
        super(message);
    }

    public InternalAppException(Throwable cause) {
        super(cause);
    }
}
