package com.soen344.ubersante.exceptions;

public class PatientNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 5861310537366287163L;

    public PatientNotFoundException() {
        super();
    }

    public PatientNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PatientNotFoundException(final String message) {
        super(message);
    }

    public PatientNotFoundException(final Throwable cause) {
        super(cause);
    }
}
