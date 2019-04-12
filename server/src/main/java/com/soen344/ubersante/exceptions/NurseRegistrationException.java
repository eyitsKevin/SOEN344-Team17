package com.soen344.ubersante.exceptions;

public class NurseRegistrationException extends RuntimeException{

    private static final long serialVersionUID = 5861310537366287163L;

    public NurseRegistrationException() {
        super();
    }

    public NurseRegistrationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NurseRegistrationException(final String message) {
        super(message);
    }

    public NurseRegistrationException(final Throwable cause) {
        super(cause);
    }
}
