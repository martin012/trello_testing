package com.trello.testing.exceptions.web;

public class WebElementException extends Exception {

    public WebElementException() {
    }

    public WebElementException(String message) {

        super(message);
    }

    public WebElementException(String message, Throwable cause) {

        super(message, cause);
    }

}
