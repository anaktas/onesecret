package com.tasos.onesecret.infra.exceptions;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends RuntimeException {
    private final HttpStatus code;
    private final String message;

    public CustomRuntimeException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomRuntimeException(HttpStatus code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public CustomRuntimeException(HttpStatus code, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public HttpStatus getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
