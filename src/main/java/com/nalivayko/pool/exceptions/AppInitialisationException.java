package com.nalivayko.pool.exceptions;

/**
 * should be called when further initialisation doesn't make sens
 */
public class AppInitialisationException extends RuntimeException {

    public AppInitialisationException(String message) {
        super(message);
    }
}
