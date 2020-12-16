package com.elsevier.usage.apitests.exception;

public class UnexpectedStatusCodeException extends RuntimeException {

    public UnexpectedStatusCodeException() {
    }

    public UnexpectedStatusCodeException(String message) {
        super(message);
    }
}
