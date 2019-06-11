package com.bvn13.jmonad.exceptions;

/**
 * Created by bvn13 on 10.06.2019.
 */
public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException() {
        super();
    }

    public ValueNotFoundException(String message) {
        super(message);
    }

    public ValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ValueNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
