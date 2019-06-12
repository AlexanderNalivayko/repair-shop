package com.nalivayko.pool.repair_shop.exceptions;

public class InternalAppException extends RuntimeException {

    public InternalAppException(String message) {
        super(message);
    }

    public InternalAppException(Throwable cause) {
        super(cause);
    }
}
