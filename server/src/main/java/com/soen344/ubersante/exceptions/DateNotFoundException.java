package com.soen344.ubersante.exceptions;

public class DateNotFoundException extends RuntimeException {

    public DateNotFoundException() {
        super();
    }

    public DateNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DateNotFoundException(final String message) {
        super(message);
    }

    public DateNotFoundException(final Throwable cause) {
        super(cause);
    }
}
