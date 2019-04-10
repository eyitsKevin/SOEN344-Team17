package com.soen344.ubersante.exceptions;

public class WalkInOverlapException extends RuntimeException{

    private static final long serialVersionUID = 5861310537366287163L;

    public WalkInOverlapException() {
    }

    public WalkInOverlapException(String message) {
        super(message);
    }

    public WalkInOverlapException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalkInOverlapException(Throwable cause) {
        super(cause);
    }

    public WalkInOverlapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
