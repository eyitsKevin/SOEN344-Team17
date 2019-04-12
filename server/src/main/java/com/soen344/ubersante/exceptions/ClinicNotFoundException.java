package com.soen344.ubersante.exceptions;

public class ClinicNotFoundException extends RuntimeException{

    public ClinicNotFoundException() {
        super();
    }

    public ClinicNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ClinicNotFoundException(final String message) {
        super(message);
    }

    public ClinicNotFoundException(final Throwable cause) {
        super(cause);
    }
}
