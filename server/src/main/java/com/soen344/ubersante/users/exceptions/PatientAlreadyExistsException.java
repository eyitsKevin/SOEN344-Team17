package com.soen344.ubersante.users.exceptions;

public class PatientAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public PatientAlreadyExistsException() {
        super();
    }

    public PatientAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PatientAlreadyExistsException(final String message) {
        super(message);
    }

    public PatientAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
