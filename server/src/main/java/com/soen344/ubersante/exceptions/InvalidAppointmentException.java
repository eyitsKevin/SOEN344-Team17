package com.soen344.ubersante.exceptions;

public class InvalidAppointmentException extends RuntimeException {

    public InvalidAppointmentException() {
        super();
    }

    public InvalidAppointmentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidAppointmentException(final String message) {
        super(message);
    }

    public InvalidAppointmentException(final Throwable cause) {
        super(cause);
    }
}
