package com.soen344.ubersante.exceptions;

public class NurseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public NurseNotFoundException() {
        super();
    }

    public NurseNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NurseNotFoundException(final String message) {
        super(message);
    }

    public NurseNotFoundException(final Throwable cause) {
        super(cause);
    }
}
