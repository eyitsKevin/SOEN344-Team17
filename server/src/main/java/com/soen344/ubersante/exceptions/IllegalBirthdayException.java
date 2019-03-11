package com.soen344.ubersante.exceptions;

public class IllegalBirthdayException extends RuntimeException {
    public IllegalBirthdayException() {
        super();
    }

    public IllegalBirthdayException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalBirthdayException(final String message) {
        super(message);
    }

    public IllegalBirthdayException(final Throwable cause) {
        super(cause);
    }
}
