package com.soen344.ubersante.exceptions;

public class EmptyCartException extends RuntimeException {
  
    public EmptyCartException() {
        super();
    }

    public EmptyCartException(String message) {
        super(message);
    }

    public EmptyCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyCartException(Throwable cause) {
        super(cause);
    }

    protected EmptyCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
