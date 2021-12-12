package com.finance.minibank.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionPayload {
    private final String message;

    private final ZonedDateTime zonedDateTime;

    private final HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }


    public ExceptionPayload(String message,
                            HttpStatus httpStatus,
                            ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }


}
