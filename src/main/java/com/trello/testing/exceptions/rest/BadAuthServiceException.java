package com.trello.testing.exceptions.rest;

public class BadAuthServiceException extends Exception {

    public BadAuthServiceException() {
    }

    public BadAuthServiceException(String message) {

        super(message);
    }

    public BadAuthServiceException(String message, Throwable cause) {

        super(message, cause);
    }

}