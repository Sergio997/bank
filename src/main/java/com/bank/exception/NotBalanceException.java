package com.bank.exception;

public class NotBalanceException extends RuntimeException {

    public NotBalanceException(String message) {
        super(message);
    }

}
