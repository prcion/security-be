package com.findork.preclinical.security.constants;

public enum ErrorCodes {
    UNNAUTHORIZED(401);

    private int errorCode;

    private ErrorCodes(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
}
