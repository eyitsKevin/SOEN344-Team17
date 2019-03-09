package com.soen344.ubersante.exceptions;

public class AvailabilityDoesNotExistException extends RuntimeException {
    public AvailabilityDoesNotExistException() {
        super();
    }

    public AvailabilityDoesNotExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AvailabilityDoesNotExistException(final String message) {
        super(message);
    }

    public AvailabilityDoesNotExistException(final Throwable cause) {
        super(cause);
    }
}
