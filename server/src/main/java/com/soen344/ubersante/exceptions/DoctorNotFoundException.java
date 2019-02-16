package com.soen344.ubersante.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException() {
        super();
    }

    public DoctorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DoctorNotFoundException(final String message) {
        super(message);
    }

    public DoctorNotFoundException(final Throwable cause) {
        super(cause);
    }
}
