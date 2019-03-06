package com.soen344.ubersante.exceptions;

public class InvalidAvailabilityException extends RuntimeException {

    public InvalidAvailabilityException() {
        super();
    }

    public InvalidAvailabilityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAvailabilityException(final String message) {
        super(message);
    }

    public InvalidAvailabilityException(final Throwable cause) {
        super(cause);
    }
}
