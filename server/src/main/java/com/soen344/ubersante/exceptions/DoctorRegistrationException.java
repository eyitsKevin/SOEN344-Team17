package com.soen344.ubersante.exceptions;

public class DoctorRegistrationException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public DoctorRegistrationException() {
        super();
    }

    public DoctorRegistrationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DoctorRegistrationException(final String message) {
        super(message);
    }

    public DoctorRegistrationException(final Throwable cause) {
        super(cause);
    }
}
