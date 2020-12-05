package com.bank.exception;

public class NotBalanceException extends RuntimeException {

    public NotBalanceException() {
    }

    public NotBalanceException(String message) {
        super(message);
    }

    public NotBalanceException(Throwable cause) {
        super(cause);
    }

    public NotBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
