package com.soen344.ubersante.exceptions;

public class AvailabilityOverlapException extends RuntimeException {

    public AvailabilityOverlapException() {
        super();
    }

    public AvailabilityOverlapException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AvailabilityOverlapException(final String message) {
        super(message);
    }

    public AvailabilityOverlapException(final Throwable cause) {
        super(cause);
    }
}
