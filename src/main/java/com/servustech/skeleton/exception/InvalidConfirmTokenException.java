package com.servustech.skeleton.exception;

public class InvalidConfirmTokenException extends RuntimeException{

    public InvalidConfirmTokenException(String message) {
        super(message);
    }
}
