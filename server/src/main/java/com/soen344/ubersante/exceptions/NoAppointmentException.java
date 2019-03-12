package com.soen344.ubersante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No Appointment Found") //404
public class NoAppointmentException extends RuntimeException {

    public NoAppointmentException() {
        super();
    }

    public NoAppointmentException(String message) {
        super(message);
    }

    public NoAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAppointmentException(Throwable cause) {
        super(cause);
    }

    protected NoAppointmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
