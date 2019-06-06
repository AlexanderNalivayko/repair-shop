package com.nalivayko.pool.exceptions;

public class UserRequestException extends RuntimeException {

    public UserRequestException(String message) {
        super(message);
    }

    public UserRequestException(Throwable cause) {
        super(cause);
    }
}
