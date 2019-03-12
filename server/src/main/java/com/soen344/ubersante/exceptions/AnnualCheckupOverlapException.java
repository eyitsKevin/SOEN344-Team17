package com.soen344.ubersante.exceptions;

public class AnnualCheckupOverlapException extends RuntimeException {
    public AnnualCheckupOverlapException() {
        super();
    }

    public AnnualCheckupOverlapException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnnualCheckupOverlapException(final String message) {
        super(message);
    }

    public AnnualCheckupOverlapException(final Throwable cause) {
        super(cause);
    }
}
