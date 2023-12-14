package com.findork.preclinical.exceptions;

public class InvalidConfirmTokenException extends RuntimeException{

    public InvalidConfirmTokenException(String message) {
        super(message);
    }
}
