package com.trello.testing.exceptions.web;

public class BrowserException extends Exception {

    public BrowserException() {
    }

    public BrowserException(String message) {

        super(message);
    }

    public BrowserException(String message, Throwable cause) {

        super(message, cause);
    }
}