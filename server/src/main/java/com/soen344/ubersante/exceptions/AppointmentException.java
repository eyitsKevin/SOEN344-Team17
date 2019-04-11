package com.soen344.ubersante.exceptions;

public class AppointmentException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public AppointmentException() {
        super();
    }

    public AppointmentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppointmentException(final String message) {
        super(message);
    }

    public AppointmentException(final Throwable cause) {
        super(cause);
    }
}
