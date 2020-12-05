package com.bank.exception;

import org.springframework.security.core.AuthenticationException;

public class CardAlreadyExistException extends AuthenticationException {

    public CardAlreadyExistException(String msg) {
        super(msg);
    }
}
